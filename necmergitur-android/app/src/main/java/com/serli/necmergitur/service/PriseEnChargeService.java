package com.serli.necmergitur.service;

import com.serli.necmergitur.model.PriseEnCharge;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by florianpires on 16/01/16.
 */
public interface PriseEnChargeService {

    @GET("/LATEST/resources/prises-en-charge")
    Call<PriseEnCharge> findPriseEnCharge(@Query("rs:id") String id);

    @POST("/LATEST/resources/prises-en-charge")
    Call<PriseEnCharge> createPriseEnCharge(@Body PriseEnCharge priseEnCharge);


}
