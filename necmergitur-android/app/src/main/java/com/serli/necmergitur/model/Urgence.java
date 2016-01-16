package com.serli.necmergitur.model;

/**
 * Created by florianpires on 16/01/16.
 */
public enum Urgence {
    UA ("Urgence Absolue"), UR("Urgence relative");

    private String name;

    Urgence(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
