package com.gestionparc.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.gestionparc.model.Mission;
import com.gestionparc.service.MissionService;
import com.gestionparc.ui.GestionParcApplication;
import java.util.List;

/**
 * Contrôleur pour la gestion des missions
 */
public class MissionController {
    @FXML
    private TableView<Mission> missionTable;

    @FXML
    private TableColumn<Mission, Integer> idColumn;

    @FXML
    private TableColumn<Mission, String> destinationColumn;

    @FXML
    private TableColumn<Mission, String> statutColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button refreshButton;

    private MissionService missionService;

    @FXML
    public void initialize() {
        this.missionService = new MissionService();
        setupTableColumns();
        loadMissions();
    }

    /**
     * Configure les colonnes du tableau
     */
    private void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getIdMission()).asObject());
        
        destinationColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDestination()));
        
        statutColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatut()));
    }

    /**
     * Charge les missions dans le tableau
     */
    private void loadMissions() {
        List<Mission> missions = missionService.missionRepository.findAll();
        ObservableList<Mission> data = FXCollections.observableArrayList(missions);
        missionTable.setItems(data);
    }

    /**
     * Rafraîchit la liste des missions
     */
    @FXML
    private void handleRefresh() {
        loadMissions();
    }

    /**
     * Retour au dashboard
     */
    @FXML
    private void handleBack() {
        try {
            GestionParcApplication.showDashboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
