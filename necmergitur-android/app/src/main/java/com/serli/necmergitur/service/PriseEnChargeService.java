package com.serli.necmergitur.service;

import com.serli.necmergitur.model.PriseEnCharge;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by florianpires on 16/01/16.
 */
public interface PriseEnChargeService {

    @GET("/api/prises-en-charge/{id}")
    Call<PriseEnCharge> findPriseEnCharge(@Path("id") String id);

    @POST("/api/prises-en-charge/{id}")
    Call<PriseEnCharge> createPriseEnCharge(@Body PriseEnCharge priseEnCharge);

}
