package com.serli.necmergitur;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.serli.necmergitur.activity.HospitalsActivity;
import com.serli.necmergitur.activity.InputActivity;
import com.serli.necmergitur.activity.QRCodeActivity;
import com.serli.necmergitur.model.PriseEnCharge;
import com.serli.necmergitur.service.PriseEnChargeService;
import com.serli.necmergitur.utils.ActivityUtils;
import com.serli.necmergitur.utils.RetrofitSingleton;
import com.serli.necmergitur.utils.TUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String ENDPOINT = "https://stub-backend-672.herokuapp.com";

    private PriseEnChargeService pecService;


    @Bind(R.id.buttonUA)
    Button buttonUA;
    @Bind(R.id.buttonUR)
    Button buttonUR;

    @Bind(R.id.buttonQRCode)
    TextView buttonQRCode;

    @Bind(R.id.buttonInput)
    TextView buttonInputText;
    @Bind(R.id.textViewInput)
    TextView textViewInput;

    @Bind(R.id.buttonHospitals)
    TextView buttonHospitals;
    @Bind(R.id.textViewHopitalChoisis)
    TextView textViewHospital;

    @Bind(R.id.buttonPhotos)
    TextView buttonPhoto;

    @Bind(R.id.panelPhotos)
    LinearLayout panelPhotos;

    private PriseEnCharge priseEnCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

        priseEnCharge = new PriseEnCharge();
        priseEnCharge.setGravite("UA");
        if(getIntent().getExtras()!=null) {
            priseEnCharge = (PriseEnCharge) getIntent().getExtras().get(ActivityUtils.PEC);
            if (priseEnCharge != null) {
                if (priseEnCharge.getHopital() != null) {
                    textViewHospital.setText(priseEnCharge.getHopital().getName());
                }
                if(priseEnCharge.getDescription()!=null) {
                    textViewInput.setText(priseEnCharge.getDescription());
                }
            }
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Button add prise en charge
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttonPriseEnCharge);
        fab.setOnClickListener(getListenerAddPriseEnCharge());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private View.OnClickListener getListenerAddPriseEnCharge(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Response response = pecService.createPriseEnCharge(priseEnCharge).execute();
                    Log.i("debugcopain",response.message());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                resetAllFields();
                Snackbar.make(view, "La prise en charge a été ajoutée.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        };
    }

    @OnClick(R.id.buttonUA)
    public void clickUA(){
        buttonUR.setEnabled(true);
        buttonUA.setEnabled(false);
        priseEnCharge.setGravite("UA");
    }

    @OnClick(R.id.buttonUR)
    public void clickUR(){
        buttonUR.setEnabled(false);
        buttonUA.setEnabled(true);
        priseEnCharge.setGravite("UR");
    }

    @OnClick(R.id.buttonQRCode)
    public void clickQRCode(){
        ActivityUtils.changeActivity(this, QRCodeActivity.class, priseEnCharge);
    }

    @OnClick(R.id.buttonHospitals)
    public void clickHospitals(){
        ActivityUtils.changeActivity(this, HospitalsActivity.class, priseEnCharge);
    }

    @OnClick(R.id.buttonInput)
    public void clickInput(){
        ActivityUtils.changeActivity(this, InputActivity.class,priseEnCharge);
    }

    @OnClick(R.id.buttonPriseEnCharge)
    public void clickAddPriseEnCharge(){
        TUtils.showToastShort(getApplicationContext(), "add prise en charge");
    }


    @OnClick(R.id.buttonPhotos)
    public void clickPhotos(){
        dispatchTakePictureIntent();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(imageBitmap);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout l =(LinearLayout) view.getParent();
                    l.removeView(view);
                }
            });
            panelPhotos.addView(imageView);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void resetAllFields(){
        String defaultText = "...";
        textViewInput.setText(defaultText);
        textViewHospital.setText(defaultText);
        panelPhotos.removeAllViews();
        buttonUA.callOnClick();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void init(){
        // MOTHERFUCKERSHITDOWN HACK
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        // INIT
        pecService = initRetrofitService();
    }

    private PriseEnChargeService initRetrofitService(){
        return RetrofitSingleton.INSTANCE.getRetrofit().create(PriseEnChargeService.class);
    }
}
