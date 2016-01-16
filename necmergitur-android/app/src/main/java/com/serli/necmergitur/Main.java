package com.serli.necmergitur;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by florianpires on 16/01/16.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));

    }
}
