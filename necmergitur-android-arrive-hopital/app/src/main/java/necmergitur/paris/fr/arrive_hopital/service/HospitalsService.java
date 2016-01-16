package necmergitur.paris.fr.arrive_hopital.service;

import java.util.List;

import necmergitur.paris.fr.arrive_hopital.model.Hopital;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by florianpires on 16/01/16.
 */
public interface HospitalsService {

    @GET("/api/hopitaux")
    Call<List<Hopital>> getHospitals();

}
