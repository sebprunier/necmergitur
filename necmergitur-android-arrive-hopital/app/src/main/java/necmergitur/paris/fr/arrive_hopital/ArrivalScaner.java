package necmergitur.paris.fr.arrive_hopital;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import necmergitur.paris.fr.arrive_hopital.model.PriseEnCharge;
import necmergitur.paris.fr.arrive_hopital.service.PriseEnChargeService;
import necmergitur.paris.fr.arrive_hopital.utils.ActivityUtils;
import necmergitur.paris.fr.arrive_hopital.utils.RetrofitSingleton;
import retrofit2.Call;
import retrofit2.Response;
//import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ArrivalScaner extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String ENDPOINT = "http://ec2-52-19-51-173.eu-west-1.compute.amazonaws.com:8080";
//    public static final String ENDPOINT = "https://stub-backend-672.herokuapp.com";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Bind(R.id.qrcode_icon)
    ImageView qrcodeIcon;
    //    @Bind(R.id.hospital_icon)
//    ImageView hospitalIcon;
//    @Bind(R.id.description_icon)
//    ImageView descriptionIcon;
//    @Bind(R.id.photo_icon)
//    ImageView photoIcon;
    @Bind(R.id.qrcode_icon_big)
    ImageView qrcodeIconBig;

    @Bind(R.id.scanLayout)
    public LinearLayout scanLayout;

    @Bind(R.id.scannedId)
    public TextView scannedId;

    @Bind(R.id.patientLayout)
    public LinearLayout patientLayout;

    @Bind(R.id.fab)
    public FloatingActionButton floatingActionButton;

    @Bind(R.id.panelPhotos)
    public LinearLayout panelPhotos;

    @Bind(R.id.description)
    public TextView description;

    @Bind(R.id.radioUA)
    public RadioButton radioUA;
    @Bind(R.id.radioUR)
    public RadioButton radioUR;
    @Bind(R.id.radioGroupGravite)
    public RadioGroup radioGroup;

    @Bind(R.id.editTextLayout)
    public LinearLayout editText;

    @Bind(R.id.editTextInput)
    public EditText editTextInput;


    private PriseEnCharge priseEnCharge = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arrival_scaner);
        ButterKnife.bind(this);

        init();

        if (getIntent().getExtras() != null) {
            priseEnCharge = (PriseEnCharge) getIntent().getExtras().get(ActivityUtils.PEC);
            updateViewWithPriseEnCharge(priseEnCharge);
        }


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
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

    @OnClick(R.id.qrcode_icon)
    public void onScanPressed() {
        new IntentIntegrator(this).initiateScan();
    }

    @OnClick(R.id.qrcode_icon_big)
    public void onBigScanPressed() {
        new IntentIntegrator(this).initiateScan();
    }

    @OnClick(R.id.fab)
    public void onTakeChargeOfButtonPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "Hopital", Toast.LENGTH_SHORT);
        toast.show();

        PriseEnChargeService priseEnChargeService = RetrofitSingleton.INSTANCE.getRetrofit().create(PriseEnChargeService.class);
        Call<PriseEnCharge> updatPriseEnCharge = priseEnChargeService.update(priseEnCharge);
        try {
            updatPriseEnCharge.execute();
        } catch (IOException e) {
            // TODO : quoi encore???
            e.printStackTrace();
        }

        resetView();
    }

    @OnClick(R.id.description)
    public void onEditText() {
        description.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
        editTextInput.setText(priseEnCharge.getDescription());
    }

    @OnClick(R.id.editTextButton)
    public void onEditTextDone() {
        description.setVisibility(View.VISIBLE);
        editText.setVisibility(View.GONE);
        String newDescription = editTextInput.getText().toString();
        priseEnCharge.setDescription(newDescription);
        this.description.setText(newDescription);

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.arrival_scaner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(imageBitmap);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout l = (LinearLayout) view.getParent();
                    l.removeView(view);
                }
            });
            priseEnCharge = (PriseEnCharge) getIntent().getExtras().get(ActivityUtils.PEC);
            panelPhotos.addView(imageView);
        }

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String sinus = result.getContents();
            if (sinus == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                priseEnCharge = new PriseEnCharge();
                PriseEnChargeService priseEnChargeService = RetrofitSingleton.INSTANCE.getRetrofit().create(PriseEnChargeService.class);
                Call<PriseEnCharge> priseEnChargeRequest = priseEnChargeService.findPriseEnCharge(sinus);
                try {
                    Response<PriseEnCharge> response = priseEnChargeRequest.execute();
                    PriseEnCharge fetchedPriseEnCharge = response.body();
                    if(fetchedPriseEnCharge == null){
                        this.priseEnCharge = new PriseEnCharge();
                        this.priseEnCharge.setId(sinus);
                    }else{
                        this.priseEnCharge = fetchedPriseEnCharge;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                updateViewWithPriseEnCharge(priseEnCharge);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateViewWithPriseEnCharge(PriseEnCharge priseEnCharge) {
//        this.priseEnCharge = priseEnCharge==null?new PriseEnCharge():priseEnCharge;
        patientLayout.setVisibility(View.VISIBLE);
        scannedId.setText("Sinux : " + this.priseEnCharge.getId());
        description.setText(this.priseEnCharge.getDescription());
        if (this.priseEnCharge.getPhotos() != null) {
            for (String urlPix : this.priseEnCharge.getPhotos()) {
                ImageView img = new ImageView(this);
                Picasso.with(getApplicationContext()).load(urlPix).into(img);
                panelPhotos.addView(img);
            }
        }
        qrcodeIconBig.setVisibility(View.GONE);
        scanLayout.setVisibility(View.VISIBLE);

        radioUA.setChecked("UA".equals(this.priseEnCharge.getGravite()));
        radioUR.setChecked("UR".equals(this.priseEnCharge.getGravite()));

        getIntent().putExtra(ActivityUtils.PEC, this.priseEnCharge);
    }

    private void resetView() {
        patientLayout.setVisibility(View.GONE);
        scannedId.setText("");
        description.setText(this.priseEnCharge.getDescription());
        panelPhotos.removeAllViews();
        radioUA.setChecked(false);
        radioUR.setChecked(false);

        qrcodeIconBig.setVisibility(View.VISIBLE);
        scanLayout.setVisibility(View.GONE);

        description.setVisibility(View.VISIBLE);
        editText.setVisibility(View.GONE);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void init() {
        // MOTHERFUCKERSHITDOWN HACK
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        drawBraceletIcon();
        drawBraceletIconBig();

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                Log.i("location", "Latitude : " + String.valueOf(location.getLatitude()) +
                        " Longitude : " + String.valueOf(location.getLongitude()));
                priseEnCharge.setLieuPrisEnCharge(String.format("%s,%s",
                        String.valueOf(location.getLatitude()),
                        String.valueOf(location.getLongitude())));
                ;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 10, locationListener);
            return;
        }

    }

    private void drawBraceletIcon() {
        Drawable qrDraw = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_watch)
                .color(Color.GRAY)
                .sizeDp(36);
        qrcodeIcon.setImageDrawable(qrDraw);
    }

    private void drawBraceletIconBig() {
        Drawable qrDraw = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_watch)
                .color(Color.GRAY)
                .sizeDp(300);
        qrcodeIconBig.setImageDrawable(qrDraw);
    }
}
