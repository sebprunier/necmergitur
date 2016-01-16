package com.serli.necmergitur.service;

import com.serli.necmergitur.model.Hopital;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by florianpires on 16/01/16.
 */
public interface HospitalsService {

    public static final String ENDPOINT = "https://stub-backend-672.herokuapp.com";

    @GET("/api/hopitaux")
    Call<List<Hopital>> getHospitals();

}
