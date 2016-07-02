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
package com.xc.java7concurrent.chapter7;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 *  XXXXXXXXXXXXXXXXXXXXX
 *
 *  @author xiachuan at 2016/7/2 15:52。
 */

public class SleepTwoSecondsTask implements Callable<String > {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }

    public static void main(String[] args) {
        MyExecutor myExecutor=new MyExecutor(2, 4, 1000, TimeUnit.
                MILLISECONDS, new LinkedBlockingDeque<Runnable>());

        List<Future<String>> results=new ArrayList<>();

        for (int i=0; i<10; i++) {
            SleepTwoSecondsTask task=new SleepTwoSecondsTask();
            Future<String> result=myExecutor.submit(task);
            results.add(result);
        }

        for (int i=0; i<5; i++){
            try {
                String result=results.get(i).get();
                System.out.printf("Main: Result for Task %d : %s\n",i,result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        myExecutor.shutdown();

        for (int i=5; i<10; i++){
            try {
                String result=results.get(i).get();
                System.out.printf("Main: Result for Task %d : %s\n",i,result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


        try {
            myExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: End of the program.\n");
    }
}

