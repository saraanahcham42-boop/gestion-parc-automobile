package com.gestionparc.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.gestionparc.model.Vehicle;
import com.gestionparc.service.VehicleService;

/**
 * Tests unitaires pour le service de gestion des véhicules
 */
public class VehicleServiceTest {
    private VehicleService vehicleService;

    @BeforeEach
    public void setUp() {
        vehicleService = new VehicleService();
    }

    @Test
    public void testGetAllVehicles() {
        assertNotNull(vehicleService.getAllVehicles());
    }

    @Test
    public void testGetAvailableVehicles() {
        assertNotNull(vehicleService.getAvailableVehicles());
    }

    @Test
    public void testGetVehiclesInMaintenance() {
        assertNotNull(vehicleService.getVehiclesInMaintenance());
    }

    @Test
    public void testVehicleStatistics() {
        assertDoesNotThrow(() -> vehicleService.displayStatistics());
    }
}
