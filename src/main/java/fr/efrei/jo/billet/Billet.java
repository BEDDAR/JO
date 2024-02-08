package fr.efrei.jo.billet;

import com.fasterxml.jackson.annotation.JsonTypeId;

import java.util.Date;

public class Billet {

    private Integer id;
    private String reference;
    private Date dateReservation;
    private Date dateEvenement;
}
