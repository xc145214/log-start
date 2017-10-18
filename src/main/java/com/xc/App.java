package com.xc;

import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {


        org.slf4j.Logger LOG = LoggerFactory.getLogger(App.class);


//        for (int i = 0; i < 10000; i++) {
//            LOG.error("Info log [" + i + "].");
//            Thread.sleep(500);
//        }

        System.out.println(Integer.valueOf(1).equals(1));
    }
}
