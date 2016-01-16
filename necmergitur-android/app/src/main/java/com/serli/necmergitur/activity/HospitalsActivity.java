package com.serli.necmergitur.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serli.necmergitur.MainActivity;
import com.serli.necmergitur.R;
import com.serli.necmergitur.adapter.HospitalAdapter;
import com.serli.necmergitur.model.Hopital;
import com.serli.necmergitur.service.HospitalsService;
import com.serli.necmergitur.utils.ActivityUtils;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by florianpires on 16/01/16.
 */
public class HospitalsActivity extends Activity {

    private ArrayAdapter<Hopital> listAdapter;
    private List<Hopital> hospitals;

    @Bind(R.id.buttonBackHospitals)
    Button buttonBack;

    @Bind(R.id.searchView)
    SearchView searchView;

    @Bind(R.id.listView)
    ListView listView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @OnClick(R.id.buttonBackHospitals)
    public void back() {
        ActivityUtils.changeActivity(this, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_hospitals);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HospitalsService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HospitalsService service = retrofit.create(HospitalsService.class);
        Call call = service.getHospitals();
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        hospitals =  (List<Hopital>) response.body();

        listAdapter = new HospitalAdapter(getApplicationContext(),R.layout.simplerow);
        listAdapter.addAll(hospitals);
        listView.setAdapter(listAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(s.toLowerCase());
                return false;
            }
        });
    }

    public void search(String name) {
        listAdapter.clear();
        for (Hopital h : hospitals) {
            if (h.getName().toLowerCase().contains(name)) {
                listAdapter.add(h);
            }
        }
        listView.setAdapter(listAdapter);
    }


}
