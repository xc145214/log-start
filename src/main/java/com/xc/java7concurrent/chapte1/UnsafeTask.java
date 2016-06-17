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
package com.xc.java7concurrent.chapte1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  使用本地线程变量。
 *
 *  @author xiachuan at 2016/6/17 16:50。
 */

public class UnsafeTask implements Runnable {

    private Date startDate;



    @Override
    public void run() {
        startDate = new Date();
        System.out.printf("Starting thread: %s : %s\n",Thread.currentThread().getId(),startDate);

        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Thread finished: %s : %s\n",Thread.currentThread().getId(),startDate);
    }


    public static void main(String[] args) {
        UnsafeTask task = new UnsafeTask();
        for (int i = 0; i <10 ; i++) {
            Thread thread = new Thread(task);
            thread.start();

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

