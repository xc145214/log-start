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

import java.util.concurrent.ThreadLocalRandom;

/**
 *  创建并发随机数。
 *  <p>
 *     在并发应用程序中使用ThreadLocalRandom生成随机数。
 *  </p>
 *
 *  @author xiachuan at 2016/7/2 14:45。
 */

public class TaskLocalRandom implements Runnable{

    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i <10 ; i++) {
            System.out.printf("%s: %d\n",name,ThreadLocalRandom.current().nextInt(10));
        }
    }


    public static void main(String[] args) {

        Thread[] threads = new Thread[3];

        for (int i = 0; i <3 ; i++) {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i]=new Thread(task);
            threads[i].start();
        }

    }
}

