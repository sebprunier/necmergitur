package com.serli.necmergitur.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.serli.necmergitur.MainActivity;
import com.serli.necmergitur.R;
import com.serli.necmergitur.model.PriseEnCharge;
import com.serli.necmergitur.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by florianpires on 16/01/16.
 */
public class InputActivity extends Activity {

    @Bind(R.id.buttonBackInput)
    Button buttonBack;

    @Bind(R.id.buttonSaveInput)
    Button buttonSaveInput;

    @Bind(R.id.editText)
    EditText editText;

    PriseEnCharge priseEnCharge;

    @OnClick(R.id.buttonBackInput)
    public void back(){
        ActivityUtils.changeActivity(this, MainActivity.class);
    }

    @OnClick(R.id.buttonSaveInput)
    public void saveInput(){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        priseEnCharge.setDescription(editText.getText().toString());
        myIntent.putExtra(ActivityUtils.PEC, priseEnCharge);
        startActivityForResult(myIntent, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_input);
        ButterKnife.bind(this);

        priseEnCharge = (PriseEnCharge)getIntent().getExtras().get(ActivityUtils.PEC);

        if(priseEnCharge!=null && priseEnCharge.getDescription()!=null){
            editText.setText(priseEnCharge.getDescription());
        }
    }


}
