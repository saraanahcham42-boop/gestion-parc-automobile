package com.gestionparc.model;

import javax.persistence.*;

/**
 * Classe Carburant - Gère la consommation de carburant des véhicules
 */
@Entity
@Table(name = "carburant")
public class Carburant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarburant;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double quantite;

    @Column(nullable = false)
    private Double prix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicule", nullable = false)
    private Vehicle vehicule;

    // Constructeurs
    public Carburant() {}

    public Carburant(String type, Double quantite, Double prix) {
        this.type = type;
        this.quantite = quantite;
        this.prix = prix;
    }

    // Getters et Setters
    public Integer getIdCarburant() {
        return idCarburant;
    }

    public void setIdCarburant(Integer idCarburant) {
        this.idCarburant = idCarburant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Vehicle getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicle vehicule) {
        this.vehicule = vehicule;
    }

    // Méthodes métier
    public void ajouterConsommation() {
        System.out.println("Ajout de consommation: " + quantite + "L de " + type);
    }

    public Double calculerCoutTotal() {
        return quantite * prix;
    }

    @Override
    public String toString() {
        return "Carburant{" +
                "idCarburant=" + idCarburant +
                ", type='" + type + '\'' +
                ", quantite=" + quantite +
                ", prix=" + prix +
                ", coutTotal=" + calculerCoutTotal() +
                '}';
    }
}
