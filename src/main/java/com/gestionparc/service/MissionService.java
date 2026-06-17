package com.gestionparc.service;

import com.gestionparc.model.Mission;
import com.gestionparc.model.Vehicle;
import com.gestionparc.model.Chauffeur;
import com.gestionparc.repository.MissionRepository;
import java.util.List;

/**
 * Service de gestion des missions
 */
public class MissionService {
    private MissionRepository missionRepository;
    private VehicleService vehicleService;

    public MissionService() {
        this.missionRepository = new MissionRepository();
        this.vehicleService = new VehicleService();
    }

    /**
     * Crée une nouvelle mission
     */
    public void createMission(Mission mission) {
        missionRepository.save(mission);
        System.out.println("Mission " + mission.getIdMission() + " créée avec succès");
    }

    /**
     * Affecte un véhicule et un chauffeur à une mission
     */
    public void assignMission(Integer missionId, Vehicle vehicle, Chauffeur chauffeur) {
        Mission mission = missionRepository.findById(missionId);
        if (mission != null) {
            mission.setVehicule(vehicle);
            mission.setChauffeur(chauffeur);
            mission.setStatut("Affectée");
            missionRepository.update(mission);
            System.out.println("Mission affectée au chauffeur et au véhicule");
        }
    }

    /**
     * Récupère les missions en attente
     */
    public List<Mission> getPendingMissions() {
        return missionRepository.findByStatut("En attente");
    }

    /**
     * Récupère les missions en cours
     */
    public List<Mission> getOngoingMissions() {
        return missionRepository.findByStatut("En cours");
    }

    /**
     * Récupère les missions d'un chauffeur
     */
    public List<Mission> getChauffeurMissions(Integer chauffeurId) {
        return missionRepository.findByChauffeurId(chauffeurId);
    }

    /**
     * Récupère les missions d'un véhicule
     */
    public List<Mission> getVehicleMissions(Integer vehicleId) {
        return missionRepository.findByVehicleId(vehicleId);
    }

    /**
     * Démarre une mission
     */
    public void startMission(Integer missionId) {
        Mission mission = missionRepository.findById(missionId);
        if (mission != null) {
            mission.demarrerMission();
            missionRepository.update(mission);
        }
    }

    /**
     * Termine une mission
     */
    public void completeMission(Integer missionId) {
        Mission mission = missionRepository.findById(missionId);
        if (mission != null) {
            mission.setStatut("Terminée");
            missionRepository.update(mission);
            System.out.println("Mission terminée");
        }
    }

    /**
     * Affiche toutes les missions
     */
    public void displayAllMissions() {
        List<Mission> missions = missionRepository.findAll();
        System.out.println("=== Toutes les missions ===");
        for (Mission m : missions) {
            System.out.println(m);
        }
    }
}
