package com.gestionparc.model;

import javax.persistence.*;

/**
 * Classe Bus - Représente un bus dans le parc automobile
 */
@Entity
@DiscriminatorValue("BUS")
@Table(name = "bus")
public class Bus extends Vehicle {
    @Column(name = "capacite_passagers")
    private Integer capacitePassagers;

    @Column(name = "type_bus")
    private String typeBus;

    // Constructeurs
    public Bus() {}

    public Bus(String immatriculation, String marque, String modele, Integer annee,
               Double kilometrage, String statut, Integer capacitePassagers, String typeBus) {
        super(immatriculation, marque, modele, annee, kilometrage, statut);
        this.capacitePassagers = capacitePassagers;
        this.typeBus = typeBus;
    }

    // Getters et Setters
    public Integer getCapacitePassagers() {
        return capacitePassagers;
    }

    public void setCapacitePassagers(Integer capacitePassagers) {
        this.capacitePassagers = capacitePassagers;
    }

    public String getTypeBus() {
        return typeBus;
    }

    public void setTypeBus(String typeBus) {
        this.typeBus = typeBus;
    }

    // Méthodes métier
    public void embarquerPassagers(Integer nombrePassagers) {
        if (nombrePassagers <= capacitePassagers) {
            System.out.println("Embarquement de " + nombrePassagers + " passagers sur le bus " + immatriculation);
        } else {
            System.out.println("Erreur: Capacité dépassée!");
        }
    }

    public void debarquerPassagers() {
        System.out.println("Débarquement des passagers du bus " + immatriculation);
    }

    @Override
    public void demarrer() {
        System.out.println("Démarrage du bus " + immatriculation);
        this.statut = "En route";
    }

    @Override
    public void arreter() {
        System.out.println("Arrêt du bus " + immatriculation);
        this.statut = "Arrêté";
    }

    @Override
    public String getTypeVehiculeString() {
        return "Bus";
    }

    @Override
    public String toString() {
        return "Bus{" +
                "immatriculation='" + immatriculation + '\'' +
                ", marque='" + marque + '\'' +
                ", capacitePassagers=" + capacitePassagers +
                ", typeBus='" + typeBus + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
