package fr.efrei.jo.client;

import fr.efrei.jo.billet.Billet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

 @Entity
public class Client {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    private String nom;
    private String prenom;
    //private List<Billet> reservations;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

   /* public List<Billet> getReservations() {
        return reservations;
    }

    public void setReservations(List<Billet> reservations) {
        this.reservations = reservations;
    }*/
}
