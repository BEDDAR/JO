package fr.efrei.jo.billet;

import fr.efrei.jo.Epreuve.AjoutEpreuves;
import fr.efrei.jo.Epreuve.Epreuve;
import fr.efrei.jo.client.Client;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String reference;
    private Date dateReservation;
    private Date dateEvenement;
    @OneToOne
    private Epreuve epreuve;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

}
