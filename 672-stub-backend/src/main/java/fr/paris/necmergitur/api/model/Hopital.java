package fr.paris.necmergitur.api.model;

import java.util.UUID;

/**
 * Created by chriswoodrow on 16/01/2016.
 */
public class Hopital {
    public String uuid = UUID.randomUUID().toString();
    public String name;
    public String location;
    public Jauge reveil;
    public Jauge urgence;
    public boolean active;

    public static class Jauge {
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
