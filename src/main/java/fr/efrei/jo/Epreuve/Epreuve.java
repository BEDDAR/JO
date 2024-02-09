package fr.efrei.jo.Epreuve;

import fr.efrei.jo.lieu.Lieu;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nom;
    private Date dateEpreuve;

    @ManyToOne
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

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

    public Date getDateEpreuve() {
        return dateEpreuve;
    }

    public void setDateEpreuve(Date dateEpreuve) {
        this.dateEpreuve = dateEpreuve;
    }
}
