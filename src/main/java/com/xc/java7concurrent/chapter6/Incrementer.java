/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package com.xc.java7concurrent.chapter6;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *  使用原子 arrays。
 *
 *  @author xiachuan at 2016/7/2 15:10。
 */

public class Incrementer implements Runnable {

    private AtomicIntegerArray vector;

    public Incrementer(AtomicIntegerArray vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i=0; i<vector.length(); i++){
            vector.getAndIncrement(i);
        }
    }


    public static void main(String[] args) {
        final int THREADS=100;
        AtomicIntegerArray vector=new AtomicIntegerArray(1000);

        Incrementer incrementer=new Incrementer(vector);

        Decrementer decrementer=new Decrementer(vector);

        Thread threadIncrementer[]=new Thread[THREADS];
        Thread threadDecrementer[]=new Thread[THREADS];

        for (int i=0; i<THREADS; i++) {
            threadIncrementer[i]=new Thread(incrementer);
            threadDecrementer[i]=new Thread(decrementer);
            threadIncrementer[i].start();
            threadDecrementer[i].start();
        }

        for (int i=0; i<100; i++) {
            try {
                threadIncrementer[i].join();
                threadDecrementer[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i=0; i<vector.length(); i++) {
            if (vector.get(i)!=0) {
                System.out.println("Vector["+i+"] : "+vector.get(i));
            }
        }
        System.out.println("Main: End of the example");

    }
}

