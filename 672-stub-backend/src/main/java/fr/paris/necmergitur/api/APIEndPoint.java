package fr.paris.necmergitur.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.necmergitur.api.model.Hopital;
import fr.paris.necmergitur.api.model.PriseEnCharge;
import net.codestory.http.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chriswoodrow on 16/01/2016.
 */
@Prefix("api")
public class APIEndPoint {
    private Map<String, Hopital> hopitaux = new HashMap<>();
    private Map<String, PriseEnCharge> priseEnCharges = new HashMap<>();
    private final String[] ETATS = {"Ambulance", "Hopital", "Sorti"};
    private final String[] GRAVITE = {"UA", "UR"};
    private final String[] TENSIONS = {"Rouge", "Orange", "Orange", "Vert", "Vert", "Vert"};

    public APIEndPoint() {
        createHopitaux();

        for (int i = 0; i < 1000; i++) {
            PriseEnCharge randomPriseEnCharge = createRandomPriseEnCharge(String.valueOf(i));
            priseEnCharges.put(randomPriseEnCharge.id, randomPriseEnCharge);
        }
    }

    @Get("hopitaux")
    @AllowOrigin("*")
    public Collection<Hopital> getHopital() {
        return hopitaux.values();
    }

    @Get("hopitaux/:hopitaUUID")
    @AllowOrigin("*")
    public Hopital getHopital(String uuid) {
        return hopitaux.get(uuid);
    }

    @Put("hopitaux/:hopitaUUID")
    @AllowOrigin("*")
    public void putHopital(String hopitaUUID, Hopital hopital) {
        hopitaux.put(hopitaUUID, hopital);
    }

    @Get("prise-en-charges")
    @AllowOrigin("*")
    public Collection<PriseEnCharge> getAllPrisesEnCharge() {
        return priseEnCharges.values();
    }

    @Get("prise-en-charges/hopital/:hopitalId")
    @AllowOrigin("*")
    public Collection<PriseEnCharge> getPrisesEnChargeParHopital(String hopitalId) {
        return priseEnCharges.entrySet()
                .stream()
                .filter(entry -> entry.getValue().hopitalUUID.equals(hopitalId))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue())).values();
    }

    @Get("prises-en-charge/:id")
    @AllowOrigin("*")
    public PriseEnCharge getPriseEnCharge(String id) {
        return priseEnCharges.get(id);
    }

    @Post("prises-en-charge/:id")
    @AllowOrigin("*")
    public void postPriseEnCharge(String id, PriseEnCharge priseEnCharge) {
        priseEnCharges.put(priseEnCharge.id, priseEnCharge);
    }

    @Put("prises-en-charge/:id")
    @AllowOrigin("*")
    public void putPriseEnCharge(String id, PriseEnCharge priseEnCharge) {
        if (priseEnCharges.containsKey(priseEnCharge)) {
            priseEnCharges.put(priseEnCharge.id, priseEnCharge);
        }
    }

    private void createHopitaux() {
        InputStream resourceAsStream = APIEndPoint.class.getResourceAsStream("/hopitaux.json");

        Hopital[] hopitauxArray;
        try {
            hopitauxArray = new ObjectMapper().readValue(resourceAsStream, Hopital[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < hopitauxArray.length; i++) {
            Hopital hopital = hopitauxArray[i];
            hopital.active = Math.random() * 10 > 1 ? true : false;
            hopital.reveil = createRandomJauge();
            hopital.urgence = createRandomJauge();
            hopital.uuid = String.valueOf(i);

            hopitaux.put(hopital.uuid, hopital);
        }
    }

    private Hopital.Jauge createRandomJauge() {
        return new Hopital.Jauge((int) (2 + Math.random() * 8), (int) (5 + Math.random() * 15), (int) (20 + Math.random() * 30), randomValue(TENSIONS));
    }

    private PriseEnCharge createRandomPriseEnCharge(String id) {
        PriseEnCharge priseEnCharge = new PriseEnCharge();
        priseEnCharge.localDateTime = LocalDateTime.now();
        priseEnCharge.etat = randomValue(ETATS);
        priseEnCharge.lieuPrisEnCharge = 2.35 + randomizeCoordiate() + "," + 48.853 + randomizeCoordiate();
        priseEnCharge.gravite = randomValue(GRAVITE);
        priseEnCharge.id = id;
        Hopital[] hopitalsArray = new ArrayList<Hopital>(hopitaux.values()).toArray(new Hopital[hopitaux.values().size()]);
        priseEnCharge.hopital = randomValue(hopitalsArray);
        priseEnCharge.hopitalUUID = priseEnCharge.hopital.uuid;

        priseEnCharge.description = "Description " + UUID.randomUUID();
        priseEnCharge.photos = new String[]{"http://www.flixya.com/files-photo/r/i/c/richer-2134233.jpg"};
        return priseEnCharge;
    }

    static double randomizeCoordiate() {
        return ((Math.random() * 2) - 1) / 10;
    }

    private <T> T randomValue(T[] strings) {
        return strings[(int) (Math.random() * strings.length)];
    }

}
