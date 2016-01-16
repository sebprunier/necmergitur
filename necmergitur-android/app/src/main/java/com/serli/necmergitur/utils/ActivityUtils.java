package com.serli.necmergitur.utils;

import android.app.Activity;
import android.content.Intent;

import com.serli.necmergitur.model.PriseEnCharge;

/**
 * Created by florianpires on 16/01/16.
 */
public class ActivityUtils {

    public static final String PEC = "PEC";

    public static void changeActivity(Activity activity, Class activityClass){
        Intent myIntent = new Intent(activity.getApplicationContext(), activityClass);
        activity.startActivityForResult(myIntent, 0);
    }

    public static void changeActivity(Activity activity, Class activityClass, PriseEnCharge priseEnCharge){
        Intent myIntent = new Intent(activity.getApplicationContext(), activityClass);
        myIntent.putExtra(PEC,priseEnCharge);
        activity.startActivityForResult(myIntent, 0);
    }
}
