package necmergitur.paris.fr.arrive_hopital.model;

import java.io.Serializable;

/**
 * Created by florianpires on 16/01/16.
 */
public class Hopital implements Serializable {
    private String uuid ;
    private String name;
    private String location;
    private Jauge reveil;
    private Jauge urgence;
    private boolean active;

    public Hopital() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Jauge getReveil() {
        return reveil;
    }

    public void setReveil(Jauge reveil) {
        this.reveil = reveil;
    }

    public Jauge getUrgence() {
        return urgence;
    }

    public void setUrgence(Jauge urgence) {
        this.urgence = urgence;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static class Jauge implements Serializable {
        public Jauge(int nombreLitsDisponibles, int nombrePatientsEnRoute, int nombreLitsOccupes, String tension) {
            this.nombreLitsDisponibles = nombreLitsDisponibles;
            this.nombrePatientsEnRoute = nombrePatientsEnRoute;
            this.nombreLitsOccupes = nombreLitsOccupes;
            this.tension = tension;
        }

        public int nombreLitsDisponibles;
        public int nombrePatientsEnRoute;
        public int nombreLitsOccupes;
        public String tension;

    }
}
