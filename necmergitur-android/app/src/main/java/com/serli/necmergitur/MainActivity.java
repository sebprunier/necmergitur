package com.serli.necmergitur;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.serli.necmergitur.activity.HospitalsActivity;
import com.serli.necmergitur.activity.InputActivity;
import com.serli.necmergitur.activity.PhotosActivity;
import com.serli.necmergitur.activity.QRCodeActivity;
import com.serli.necmergitur.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.buttonUR)
    Button buttonUR;
    @Bind(R.id.buttonUA)
    Button buttonUA;
    @Bind(R.id.buttonQRCode)
    Button buttonQRCode;
    @Bind(R.id.buttonPhotos)
    Button buttonPhoto;
    @Bind(R.id.buttonInput)
    Button buttonInputText;
    @Bind(R.id.buttonSave)
    Button buttonSave;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        initScreen();

//        buttonTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), TestActivity.class);
//                startActivityForResult(myIntent, 0);
//            }
//        });

        if(getIntent().getExtras() != null) {
            String content = getIntent().getExtras().get("content") != null ? getIntent().getExtras().get("content").toString() : null;
            if (content != null) {
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.buttonUA)
    public void clickUA(){
        buttonUR.setEnabled(true);
        buttonUA.setEnabled(false);
    }

    @OnClick(R.id.buttonUR)
    public void clickUR(){
        buttonUR.setEnabled(false);
        buttonUA.setEnabled(true);
    }

    @OnClick(R.id.buttonQRCode)
    public void clickQRCode(){
        ActivityUtils.changeActivity(this, QRCodeActivity.class);
    }

    @OnClick(R.id.buttonHospitals)
    public void clickHospitals(){
        ActivityUtils.changeActivity(this, HospitalsActivity.class);
    }

    @OnClick(R.id.buttonInput)
    public void clickInput(){
        ActivityUtils.changeActivity(this, InputActivity.class);
    }


    @OnClick(R.id.buttonPhotos)
    public void clickPhotos(){
        ActivityUtils.changeActivity(this, PhotosActivity.class);
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
}
