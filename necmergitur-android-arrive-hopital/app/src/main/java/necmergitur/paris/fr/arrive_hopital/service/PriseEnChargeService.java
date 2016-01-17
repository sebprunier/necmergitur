package necmergitur.paris.fr.arrive_hopital.service;

import necmergitur.paris.fr.arrive_hopital.model.PriseEnCharge;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by florianpires on 16/01/16.
 */
public interface PriseEnChargeService {

//    @GET("/api/prises-en-charge/{id}")
//    Call<PriseEnCharge> findPriseEnCharge(@Path("id") String id);
//
//    @POST("/api/prises-en-charge/{id}")
//    Call<PriseEnCharge> createPriseEnCharge(@Body PriseEnCharge priseEnCharge);

    @GET("/LATEST/resources/prises-en-charge")
    Call<PriseEnCharge> findPriseEnCharge(@Query("rs:id") String id);

    @POST("/LATEST/resources/prises-en-charge")
    Call<PriseEnCharge> createPriseEnCharge(@Body PriseEnCharge priseEnCharge);

    @PUT("/LATEST/resources/prises-en-charge")
    Call<PriseEnCharge> update(@Body PriseEnCharge priseEnCharge);

}
