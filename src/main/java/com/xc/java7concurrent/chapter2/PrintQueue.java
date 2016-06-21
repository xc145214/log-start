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
package com.xc.java7concurrent.chapter2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock同步代码块。
 * <p>
 * 通过锁来同步代码块和通过Lock接口及其实现者ReentrantLock类来创建临界区，实现一个程序来模拟打印队列。
 * </p>
 *
 * @author xiachuan at 2016/6/21 15:52。
 */

public class PrintQueue {

    private final Lock queueLock = new ReentrantLock();


    /**
     * 当我们通过锁来实现一个临界区并且保证只有一个执行线程能运行一个代码块，我们必 须创建一个ReentrantLock对象。
     * 在临界区的起始部分，我们必须通过使用lock()方法来获得锁的控制权。当一个线程A调用这个方法时，如果 没有其他线程持有这个锁的控制权，那么这个方法就会给线程A分配这个锁的控制权并且立即返回允许线程A执行这个临界区。
     * 否则，如果其他线程B正在执行由这 个锁控制的临界区，lock()方法将会使线程A睡眠直到线程B完成这个临界区的执行。
     * <p/>
     * 在临界区的尾部，我们必须使用unlock()方法来释放锁的控制权，允许其他线程运行这个临界区。
     * 如果你在临界区的尾部没有调用unlock()方法，那么其他正在等待该代码块的线程将会永远等待，造成 死锁情况。
     * 如果你在临界区使用try-catch代码块，别忘了在finally部分的内部包含unlock()方法的代码。
     *
     * @param document
     */
    public void printJob(Object document) {
        queueLock.lock();

        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) +
                    " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

    }


    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue), "Thread " + i);
        }


        for (int i = 0; i < 10; i++) {
            threads[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}

