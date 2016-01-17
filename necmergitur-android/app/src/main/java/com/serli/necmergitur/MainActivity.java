package com.serli.necmergitur;

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
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.serli.necmergitur.activity.HospitalsActivity;
import com.serli.necmergitur.activity.InputActivity;
import com.serli.necmergitur.model.PriseEnCharge;
import com.serli.necmergitur.model.TensionColor;
import com.serli.necmergitur.service.PriseEnChargeService;
import com.serli.necmergitur.utils.ActivityUtils;
import com.serli.necmergitur.utils.RetrofitSingleton;
import com.serli.necmergitur.utils.TUtils;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    public static final String ENDPOINT = "http://ec2-52-19-51-173.eu-west-1.compute.amazonaws.com:8080";

    private final static String[] PHOTOS = {
            "http://f.hypotheses.org/wp-content/blogs.dir/855/files/2015/07/L%C3%A9onard-de-Vinci-Anatomie-672x372.jpg",
            "http://www.atlantico.fr/sites/atlantico.fr/files/styles/une/public/images/2014/04/vitruve_de_leonard_de_vinci.jpg",
            "https://s-media-cache-ak0.pinimg.com/736x/45/60/d3/4560d3b309968a340e31ef54da36e4fe.jpg"
    };

    private PriseEnChargeService pecService;

    @Bind(R.id.qrcode_icon)
    ImageView qrcodeIcon;
    @Bind(R.id.hospital_icon)
    ImageView hospitalIcon;
    @Bind(R.id.description_icon)
    ImageView descriptionIcon;
    @Bind(R.id.photo_icon)
    ImageView photoIcon;


    @Bind(R.id.buttonUA)
    Button buttonUA;
    @Bind(R.id.buttonUR)
    Button buttonUR;

    @Bind(R.id.layoutQRCode)
    RelativeLayout layoutQRCode;

    @Bind(R.id.textViewInput)
    TextView textViewInput;

    @Bind(R.id.textViewHopitalChoisis)
    TextView textViewHospital;

    @Bind(R.id.panelPhotos)
    LinearLayout panelPhotos;

    @Bind(R.id.layoutHopital)
    RelativeLayout layoutHopital;

    @Bind(R.id.layoutInput)
    RelativeLayout layoutInput;

    @Bind(R.id.layoutPhotos)
    RelativeLayout layoutPhotos;

    private PriseEnCharge priseEnCharge= new PriseEnCharge();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
        priseEnCharge.setGravite("UA");
        if (getIntent().getExtras() != null) {
            priseEnCharge = (PriseEnCharge) getIntent().getExtras().get(ActivityUtils.PEC);
            if (priseEnCharge != null) {
                if (priseEnCharge.getHopital() != null) {
                    textViewHospital.setText(priseEnCharge.getHopital().getName());

                    String tension = priseEnCharge.getHopital().getReveil().tension;
                    hospitalIcon.setColorFilter(getResources().getColor(TensionColor.findCode(tension)));
                }
                if (priseEnCharge.getDescription() != null) {
                    textViewInput.setText(priseEnCharge.getDescription());
                }
            }
        }

        if(!textViewHospital.getText().equals("...")){
//         layoutHopital.setBackgroundColor(getResources().getColor(R.color.nice_grey));
        }
        if(!textViewInput.getText().equals("...") || textViewInput.getText().equals("")){
//            layoutInput.setBackgroundColor(getResources().getColor(R.color.nice_grey));
        }

        // Button add prise en charge
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttonPriseEnCharge);
        fab.setOnClickListener(getListenerAddPriseEnCharge());


    }

    private View.OnClickListener getListenerAddPriseEnCharge() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(priseEnCharge.getId()!=null && !priseEnCharge.getId().isEmpty()) {
//                    String id = String.valueOf(new Random().nextInt((1000 - 20) + 1) + 20);
//                    priseEnCharge.setId(id);
//                }
                priseEnCharge.setEtat("Transport");

                GregorianCalendar c = new GregorianCalendar();
                c.setTime(c.getTime());
                try {
                    String dateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date());
                    priseEnCharge.setLocalDateTime(dateTime);
                    priseEnCharge.setHopitalUUID(priseEnCharge.getHopital().getUuid());
                    priseEnCharge.setPhotos(new ArrayList<String>(Arrays.asList(randomValue(PHOTOS))));
                    if(textViewInput.getText()==null) priseEnCharge.setDescription("...");
                    if(!buttonUA.isEnabled()){
                        priseEnCharge.setGravite("UA");
                    }else{
                        priseEnCharge.setGravite("UR");
                    }
                    Response response = pecService.createPriseEnCharge(priseEnCharge).execute();
                    Log.i("response", response.body().toString());
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
    public void clickUA() {
        buttonUR.setEnabled(true);
        buttonUA.setEnabled(false);
        if(priseEnCharge!=null) priseEnCharge.setGravite("UA");
    }

    @OnClick(R.id.buttonUR)
    public void clickUR() {
        buttonUR.setEnabled(false);
        buttonUA.setEnabled(true);
        if(priseEnCharge!=null)  priseEnCharge.setGravite("UR");
    }

    @OnClick(R.id.layoutQRCode)
    public void clickQRCode() {
        new IntentIntegrator(this).initiateScan();
    }

    @OnClick(R.id.layoutHopital)
    public void clickHospitals() {
        ActivityUtils.changeActivity(this, HospitalsActivity.class, priseEnCharge);
    }

    @OnClick(R.id.layoutInput)
    public void clickInput() {
        ActivityUtils.changeActivity(this, InputActivity.class, priseEnCharge);
    }

    @OnClick(R.id.buttonPriseEnCharge)
    public void clickAddPriseEnCharge() {
        TUtils.showToastShort(getApplicationContext(), "add prise en charge");
    }


    @OnClick(R.id.layoutPhotos)
    public void clickPhotos() {
        dispatchTakePictureIntent();
    }

    private PriseEnCharge  loadData(String id) {
        Response response = null;
        panelPhotos.removeAllViews();
        try {
            response = pecService.findPriseEnCharge(id).execute();
            PriseEnCharge pecFound = (PriseEnCharge) response.body();
            if (pecFound != null) {
                if (pecFound.getEtat() != null) {
                    if ("UA".equals(pecFound.getEtat())) {
                        buttonUA.callOnClick();
                    } else {
                        buttonUR.callOnClick();
                    }
                }
                if (pecFound.getHopital() != null) {
                    textViewHospital.setText(pecFound.getHopital().getName());
                    if(pecFound.getHopital().getReveil()!=null) {
                        String tension = pecFound.getHopital().getReveil().tension;
                        hospitalIcon.setColorFilter(getResources().getColor(TensionColor.findCode(tension)));
                    }
                }
                if (pecFound.getDescription() != null) {
                    textViewInput.setText(pecFound.getDescription());
                }
                if (pecFound.getPhotos() != null) {
                    for (String urlPix : pecFound.getPhotos()) {
                        ImageView img = new ImageView(this);
                        Picasso.with(getApplicationContext()).load(urlPix).into(img);
                        panelPhotos.addView(img);
                    }
                }
                return pecFound;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PriseEnCharge prise = new PriseEnCharge();
        prise.setId(id);
        if(buttonUA.isEnabled()){
            prise.setGravite("UA");
        }else{
            prise.setGravite("UR");
        }

        return prise;
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
            panelPhotos.addView(imageView);
        }

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
            } else {
                String id = result.getContents();
                priseEnCharge = loadData(id);
                layoutQRCode.setBackgroundColor(getResources().getColor(R.color.nice_grey));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void resetAllFields() {
        String defaultText = "...";
        textViewInput.setText(defaultText);
        textViewHospital.setText(defaultText);
        panelPhotos.removeAllViews();
        buttonUA.callOnClick();
        layoutHopital.setBackgroundColor(Color.WHITE);
        layoutQRCode.setBackgroundColor(Color.WHITE);
        layoutPhotos.setBackgroundColor(Color.WHITE);
        layoutInput.setBackgroundColor(Color.WHITE);
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

        buttonUA.setEnabled(false);
        buttonUR.setEnabled(true);
        // INIT
        pecService = initRetrofitService();
        Integer size = 36;
        Drawable qrDraw = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_blur)
                .color(Color.GRAY)
                .sizeDp(size);
        Drawable hospitalDraw = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_hospital)
                .color(Color.GRAY)
                .sizeDp(size);
        Drawable descriptionDraw = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_format_align_left)
                .color(Color.GRAY)
                .sizeDp(size);
        Drawable photoDraw = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_camera_add)
                .color(Color.GRAY)
                .sizeDp(size);
        qrcodeIcon.setImageDrawable(qrDraw);
        hospitalIcon.setImageDrawable(hospitalDraw);
        descriptionIcon.setImageDrawable(descriptionDraw);
        photoIcon.setImageDrawable(photoDraw);

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                Log.i("location", "Latitude : " + String.valueOf(location.getLatitude()) +
                        " Longitude : " + String.valueOf(location.getLongitude()));
                if(priseEnCharge!=null) {
                    priseEnCharge.setLieuPrisEnCharge(String.format("%s,%s",
                            String.valueOf(location.getLatitude()),
                            String.valueOf(location.getLongitude())));
                    ;
                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, locationListener);


        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

    }

    private PriseEnChargeService initRetrofitService(){
        return RetrofitSingleton.INSTANCE.getRetrofit().create(PriseEnChargeService.class);
    }

    private static <T> T randomValue(T[] strings) {
        return strings[(int) (Math.random() * strings.length)];
    }

}
