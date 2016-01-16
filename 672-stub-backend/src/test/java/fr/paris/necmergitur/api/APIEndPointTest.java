package fr.paris.necmergitur.api;

import org.junit.Test;

/**
 * Created by chriswoodrow on 16/01/2016.
 */
public class APIEndPointTest {
@Test
    public void test(){
        for(int i =0; i< 100; i++){
            System.out.println(APIEndPoint.randomizeCoordiate());
        }
    }
}
