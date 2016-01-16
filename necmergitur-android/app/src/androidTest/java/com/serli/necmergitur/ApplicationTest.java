package com.serli.necmergitur;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public static void main(String[] args) {
        System.out.println( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
        System.out.println(((Math.random() * 100) - 1)/10);

    }
    public void test(){
    }
}