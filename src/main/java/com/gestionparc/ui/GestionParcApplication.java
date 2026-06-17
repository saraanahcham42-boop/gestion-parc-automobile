package com.gestionparc.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.gestionparc.model.Utilisateur;

/**
 * Classe principale de l'application GUI JavaFX
 */
public class GestionParcApplication extends Application {
    private static Utilisateur currentUser;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showLoginScene();
        stage.setTitle("Gestion Parc Automobile");
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.show();
    }

    /**
     * Affiche l'écran de connexion
     */
    public static void showLoginScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(GestionParcApplication.class.getResource("/fxml/login.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
    }

    /**
     * Affiche le dashboard principal après connexion
     */
    public static void showDashboard() throws Exception {
        FXMLLoader loader = new FXMLLoader(GestionParcApplication.class.getResource("/fxml/dashboard.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
    }

    /**
     * Affiche la page de gestion des véhicules
     */
    public static void showVehicleManagement() throws Exception {
        FXMLLoader loader = new FXMLLoader(GestionParcApplication.class.getResource("/fxml/vehicles.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
    }

    /**
     * Affiche la page de gestion des missions
     */
    public static void showMissionManagement() throws Exception {
        FXMLLoader loader = new FXMLLoader(GestionParcApplication.class.getResource("/fxml/missions.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
    }

    /**
     * Affiche la page de gestion de la maintenance
     */
    public static void showMaintenanceManagement() throws Exception {
        FXMLLoader loader = new FXMLLoader(GestionParcApplication.class.getResource("/fxml/maintenance.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
    }

    /**
     * Définit l'utilisateur actuellement connecté
     */
    public static void setCurrentUser(Utilisateur user) {
        currentUser = user;
    }

    /**
     * Récupère l'utilisateur actuellement connecté
     */
    public static Utilisateur getCurrentUser() {
        return currentUser;
    }

    /**
     * Déconnexion
     */
    public static void logout() throws Exception {
        currentUser = null;
        showLoginScene();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
