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
package com.xc.java7concurrent.chapter3;

import java.util.concurrent.Semaphore;

/**
 * 控制并发访问资源。
 * <p>
 * 学习如何使用Semaphore类来实现一种比较特殊的semaphores种类，称为binary semaphores。这个semaphores种类保护访问共享资源的独特性，所以semaphore的内部计数器的值只能是1或者0。为了展示如何使用它，你将要实现一个PrintQueue类来让并发任务打印它们的任务。
 * 这个PrintQueue类会受到binary semaphore的保护，所以每次只能有一个线程可以打印。
 * </p>
 *
 * @author xiachuan at 2016/6/21 17:38。
 */

public class PrintQueue {


    private final Semaphore semaphore; //计数器

    public PrintQueue() {
        this.semaphore = new Semaphore(1);
    }

    public void printJob(Object document) {

        try {
            semaphore.acquire();

            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }


    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();


        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}

