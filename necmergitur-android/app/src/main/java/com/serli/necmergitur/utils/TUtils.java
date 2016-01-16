package com.serli.necmergitur.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by florianpires on 16/01/16.
 */
public class TUtils {

    public static void showToastShort(Context ctx, String content){
        Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context ctx, String content){
        Toast.makeText(ctx, content, Toast.LENGTH_LONG).show();
    }
}
