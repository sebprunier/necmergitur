package com.serli.necmergitur.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.serli.necmergitur.MainActivity;
import com.serli.necmergitur.R;
import com.serli.necmergitur.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by florianpires on 15/01/16.
 */
public class PhotosActivity extends Activity{


    @Bind(R.id.buttonBackPhotos)
    Button buttonBack;

    @OnClick(R.id.buttonBackPhotos)
    public void back(){
        ActivityUtils.changeActivity(this, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_photos);
        ButterKnife.bind(this);
    }
}
