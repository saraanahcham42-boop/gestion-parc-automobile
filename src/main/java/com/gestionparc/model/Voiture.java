package com.gestionparc.model;

import javax.persistence.*;

/**
 * Classe Voiture - Représente une voiture dans le parc automobile
 */
@Entity
@DiscriminatorValue("VOITURE")
@Table(name = "voiture")
public class Voiture extends Vehicle {
    @Column(name = "nombre_places")
    private Integer nombrePlaces;

    @Column(name = "type_carburant")
    private String typeCarburant;

    // Constructeurs
    public Voiture() {}

    public Voiture(String immatriculation, String marque, String modele, Integer annee,
                   Double kilometrage, String statut, Integer nombrePlaces, String typeCarburant) {
        super(immatriculation, marque, modele, annee, kilometrage, statut);
        this.nombrePlaces = nombrePlaces;
        this.typeCarburant = typeCarburant;
    }

    // Getters et Setters
    public Integer getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(Integer nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public void setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    // Méthodes métier
    public void reserver() {
        System.out.println("Réservation de la voiture " + immatriculation);
        this.statut = "Réservée";
    }

    public void liberer() {
        System.out.println("Libération de la voiture " + immatriculation);
        this.statut = "En service";
    }

    @Override
    public void demarrer() {
        System.out.println("Démarrage de la voiture " + immatriculation);
        this.statut = "En route";
    }

    @Override
    public void arreter() {
        System.out.println("Arrêt de la voiture " + immatriculation);
        this.statut = "Arrêtée";
    }

    @Override
    public String getTypeVehiculeString() {
        return "Voiture";
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "immatriculation='" + immatriculation + '\'' +
                ", marque='" + marque + '\'' +
                ", nombrePlaces=" + nombrePlaces +
                ", typeCarburant='" + typeCarburant + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
