package com.serli.necmergitur.model;

import android.graphics.Color;

import com.serli.necmergitur.R;

/**
 * Created by florianpires on 17/01/16.
 */
public enum TensionColor {
    VERT("Vert", R.color.nice_green),JAUNE("Jaune",R.color.nice_yellow),ORANGE("Orange",R.color.nice_orange),ROUGE("Rouge", R.color.nice_red),NOIR("Noir", Color.BLACK);

    private String couleur;
    private int code;

    TensionColor(String couleur, int code){
        this.couleur = couleur;
        this.code = code;
    }

    public String getCouleur(){
        return this.couleur;
    }

    public int getCode(){
        return this.code;
    }

    public static Integer findCode(String tension){
        for(TensionColor tensionColor :TensionColor.values()){
            if(tension.equals(tensionColor.getCouleur())) return tensionColor.getCode();
        }
        return null;
    }
}
