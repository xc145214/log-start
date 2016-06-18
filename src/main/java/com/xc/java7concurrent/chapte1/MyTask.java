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

import java.util.Random;

/**
 * 处理线程组里的异常。
 *
 * @author xiachuan at 2016/6/18 15:46。
 */

public class MyTask implements Runnable {

    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true) {
            result = 1000 /((int) (random.nextDouble() * 1000));
            System.out.printf("%s : %d\n", Thread.currentThread().
                    getId(), result);
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("%d : Interrupted\n", Thread.
                        currentThread().getId());
                return;
            }
        }
    }


    public static void main(String[] args) {
        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
        MyTask task = new MyTask();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(threadGroup, task);
            t.start();
        }

    }
}

