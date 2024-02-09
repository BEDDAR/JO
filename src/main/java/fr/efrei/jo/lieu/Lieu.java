package fr.efrei.jo.lieu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.efrei.jo.Epreuve.Epreuve;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nom;
    private String adresse;

    @OneToMany(mappedBy = "lieu")
    @JsonIgnore
    private List<Epreuve> epreuves = new ArrayList<>();

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

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }
    public void setEpreuves(List<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }
}
