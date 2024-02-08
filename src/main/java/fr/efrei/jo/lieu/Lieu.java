package fr.efrei.jo.lieu;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Lieu {
    @Id
    private Integer id;
    private String nom;
    private String adresse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
