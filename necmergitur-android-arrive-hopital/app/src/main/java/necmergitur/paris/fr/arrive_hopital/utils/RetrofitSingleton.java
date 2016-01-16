package necmergitur.paris.fr.arrive_hopital.utils;

import necmergitur.paris.fr.arrive_hopital.ArrivalScaner;
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
                    .baseUrl(ArrivalScaner.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return  retrofit;
    }
}
