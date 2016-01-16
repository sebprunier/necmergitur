package com.serli.necmergitur.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by florianpires on 16/01/16.
 */
public class ActivityUtils {

    public static void changeActivity(Activity activity, Class activityClass){
        Intent myIntent = new Intent(activity.getApplicationContext(), activityClass);
        activity.startActivityForResult(myIntent, 0);
    }
}
