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

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *
 *
 *  @author xiachuan at 2016/6/30 20:23。
 */

public class PollTask implements Runnable{

    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i=0; i<5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list=new
                ConcurrentLinkedDeque<>();

        Thread threads[]=new Thread[100];
        for (int i=0; i<threads.length ; i++){
            AddTask task=new AddTask(list);
            threads[i]=new Thread(task);
            threads[i].start();
        }

        System.out.printf("Main: %d AddTask threads have been launched\n",threads.length);


        for (int i=0; i<threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d\n",list.size());

        for (int i=0; i< threads.length; i++){
            PollTask task=new PollTask(list);
            threads[i]=new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d PollTask threads have been launched\n",threads.length);

        for (int i=0; i<threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d\n",list.size());

    }
}

