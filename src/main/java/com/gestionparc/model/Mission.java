package com.gestionparc.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe Mission - Représente une mission d'un chauffeur avec un véhicule
 */
@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMission;

    @Column(nullable = false)
    private LocalDate dateDepart;

    @Column(nullable = false)
    private LocalDate dateRetour;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String statut; // En attente, En cours, Terminée, Annulée

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicule", nullable = false)
    private Vehicle vehicule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chauffeur", nullable = false)
    private Chauffeur chauffeur;

    // Constructeurs
    public Mission() {}

    public Mission(LocalDate dateDepart, LocalDate dateRetour, String destination) {
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.destination = destination;
        this.statut = "En attente";
    }

    // Getters et Setters
    public Integer getIdMission() {
        return idMission;
    }

    public void setIdMission(Integer idMission) {
        this.idMission = idMission;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Vehicle getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicle vehicule) {
        this.vehicule = vehicule;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    // Méthodes métier
    public void affecterVehicule(Vehicle vehicle) {
        this.vehicule = vehicle;
        vehicle.getMissions().add(this);
        System.out.println("Véhicule " + vehicle.getImmatriculation() + " affecté à la mission");
    }

    public void affecterChauffeur(Chauffeur driver) {
        this.chauffeur = driver;
        driver.getMissions().add(this);
        System.out.println("Chauffeur " + driver.getNomComplet() + " affecté à la mission");
    }

    public void demarrerMission() {
        this.statut = "En cours";
        System.out.println("Mission " + idMission + " démarrée");
    }

    @Override
    public String toString() {
        return "Mission{" +
                "idMission=" + idMission +
                ", dateDepart=" + dateDepart +
                ", destination='" + destination + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
