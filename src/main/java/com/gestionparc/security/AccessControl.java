package com.gestionparc.security;

import com.gestionparc.model.Utilisateur;

/**
 * Interface de contrôle d'accès basée sur les rôles (RBAC)
 */
public class AccessControl {

    /**
     * Vérifie si un utilisateur a le droit d'accéder à une ressource
     */
    public static boolean hasAccess(Utilisateur utilisateur, String action) {
        if (utilisateur == null) {
            return false;
        }

        String role = utilisateur.getRoleString();

        switch (action) {
            // Actions Admin
            case "MANAGE_USERS":
            case "DELETE_VEHICLE":
            case "MANAGE_SYSTEM":
                return role.equals("Administrateur");

            // Actions Chef
            case "MANAGE_MISSIONS":
            case "VIEW_REPORTS":
            case "ASSIGN_VEHICLES":
                return role.equals("Administrateur") || role.equals("Chef");

            // Actions Chauffeur
            case "VIEW_MY_MISSIONS":
            case "UPDATE_MISSION_STATUS":
                return role.equals("Chauffeur") || isManager(role);

            // Actions Technicien
            case "MANAGE_MAINTENANCE":
            case "VIEW_MAINTENANCE":
                return role.equals("Administrateur") || role.equals("Technicien Maintenance");

            // Actions communes
            case "VIEW_VEHICLES":
                return true;

            default:
                return false;
        }
    }

    /**
     * Vérifie si un utilisateur est un manager
     */
    private static boolean isManager(String role) {
        return role.equals("Administrateur") || role.equals("Chef");
    }

    /**
     * Obtient le niveau d'accès d'un utilisateur
     */
    public static int getAccessLevel(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return 0;
        }

        String role = utilisateur.getRoleString();
        switch (role) {
            case "Administrateur":
                return 5;
            case "Chef":
                return 3;
            case "Technicien Maintenance":
                return 2;
            case "Chauffeur":
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Vérifie si l'utilisateur peut modifier une mission
     */
    public static boolean canModifyMission(Utilisateur utilisateur, String missionOwner) {
        String role = utilisateur.getRoleString();
        
        if (role.equals("Administrateur") || role.equals("Chef")) {
            return true;
        }
        
        if (role.equals("Chauffeur")) {
            return utilisateur.getNomComplet().equals(missionOwner);
        }
        
        return false;
    }

    /**
     * Vérifie si l'utilisateur peut accéder au dashboard d'un autre utilisateur
     */
    public static boolean canAccessUserDashboard(Utilisateur loggedUser, Utilisateur targetUser) {
        if (loggedUser.getIdUtilisateur().equals(targetUser.getIdUtilisateur())) {
            return true;
        }

        String role = loggedUser.getRoleString();
        return role.equals("Administrateur") || role.equals("Chef");
    }
}
