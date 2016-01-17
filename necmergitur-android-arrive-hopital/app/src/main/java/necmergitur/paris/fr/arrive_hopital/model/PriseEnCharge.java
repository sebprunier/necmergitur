package necmergitur.paris.fr.arrive_hopital.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by florianpires on 16/01/16.
 */
public class PriseEnCharge implements Serializable {

    /**
     * localDateTime : 2016-01-16T10:31:28.517
     * etat : Ambulance
     * lieuPrisEnCharge : 2.3660252400751043,48.853-0.055120032708947254
     * gravite : UA
     * id : 0
     * hopital : {"uuid":"15","name":"Hôpital Européen Georges-Pompidou","location":"48.8393498,2.273569500000008","reveil":{"nombreLitsDisponibles":7,"nombrePatientsEnRoute":7,"nombreLitsOccupes":38,"tension":"Rouge"},"urgence":{"nombreLitsDisponibles":7,"nombrePatientsEnRoute":19,"nombreLitsOccupes":37,"tension":"Vert"},"active":true}
     * hopitalUUID : 15
     * description : Description 2d73cd1a-ae73-4033-9b63-3e573da4643c
     * photos : ["http://www.flixya.com/files-photo/r/i/c/richer-2134233.jpg"]
     */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private String localDateTime = dateFormat.format(new Date());
    private String etat = "Hopital";
    private String lieuPrisEnCharge = "";
    private String gravite = "UR";
    private String id = "";
    /**
     * uuid : 15
     * name : Hôpital Européen Georges-Pompidou
     * location : 48.8393498,2.273569500000008
     * reveil : {"nombreLitsDisponibles":7,"nombrePatientsEnRoute":7,"nombreLitsOccupes":38,"tension":"Rouge"}
     * urgence : {"nombreLitsDisponibles":7,"nombrePatientsEnRoute":19,"nombreLitsOccupes":37,"tension":"Vert"}
     * active : true
     */

    private Hopital hopital = new Hopital();
    private String hopitalUUID ="";
    private String description = "";
    private List<String> photos = new ArrayList<>();

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setLieuPrisEnCharge(String lieuPrisEnCharge) {
        this.lieuPrisEnCharge = lieuPrisEnCharge;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHopital(Hopital hopital) {
        this.hopital = hopital;
    }

    public void setHopitalUUID(String hopitalUUID) {
        this.hopitalUUID = hopitalUUID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public String getEtat() {
        return etat;
    }

    public String getLieuPrisEnCharge() {
        return lieuPrisEnCharge;
    }

    public String getGravite() {
        return gravite;
    }

    public String getId() {
        return id;
    }

    public Hopital getHopital() {
        return hopital;
    }

    public String getHopitalUUID() {
        return hopitalUUID;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPhotos() {
        return photos;
    }
}
