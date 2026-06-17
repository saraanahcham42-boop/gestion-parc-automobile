package com.gestionparc.service;

import com.gestionparc.model.Maintenance;
import com.gestionparc.repository.MaintenanceRepository;
import java.util.List;

/**
 * Service de gestion de la maintenance
 */
public class MaintenanceService {
    private MaintenanceRepository maintenanceRepository;

    public MaintenanceService() {
        this.maintenanceRepository = new MaintenanceRepository();
    }

    /**
     * Crée une nouvelle maintenance
     */
    public void createMaintenance(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
        System.out.println("Maintenance planifiée avec succès");
    }

    /**
     * Récupère les maintenances d'un véhicule
     */
    public List<Maintenance> getVehicleMaintenanceHistory(Integer vehicleId) {
        return maintenanceRepository.findByVehicleId(vehicleId);
    }

    /**
     * Récupère les maintenances par type
     */
    public List<Maintenance> getMaintenanceByType(String type) {
        return maintenanceRepository.findByType(type);
    }

    /**
     * Calcule le coût total de maintenance pour un véhicule
     */
    public Double calculateTotalMaintenanceCost(Integer vehicleId) {
        List<Maintenance> maintenances = getVehicleMaintenanceHistory(vehicleId);
        return maintenances.stream()
            .mapToDouble(Maintenance::getCout)
            .sum();
    }

    /**
     * Affiche l'historique de maintenance
     */
    public void displayMaintenanceHistory(Integer vehicleId) {
        List<Maintenance> maintenances = getVehicleMaintenanceHistory(vehicleId);
        System.out.println("=== Historique de maintenance ===");
        for (Maintenance m : maintenances) {
            System.out.println(m);
        }
        System.out.println("Coût total: " + calculateTotalMaintenanceCost(vehicleId) + "€");
    }
}
