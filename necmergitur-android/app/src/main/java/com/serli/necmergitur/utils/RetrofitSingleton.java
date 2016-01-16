package com.serli.necmergitur.utils;

import com.serli.necmergitur.MainActivity;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by florianpires on 16/01/16.
 */
public enum RetrofitSingleton {

    INSTANCE;

    Retrofit retrofit;

    public Retrofit getRetrofit() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return  retrofit;
    }
}
