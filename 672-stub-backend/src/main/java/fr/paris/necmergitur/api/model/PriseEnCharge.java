package fr.paris.necmergitur.api.model;

import java.time.LocalDateTime;

/**
 * Created by chriswoodrow on 16/01/2016.
 */
public class PriseEnCharge {
    public LocalDateTime localDateTime = LocalDateTime.now();
    public String etat;
    public String lieuPrisEnCharge;
    public String gravite;
    public String id;
    public Hopital hopital;
    public String hopitalUUID;
    public String description;
    public String[] photos;
}
