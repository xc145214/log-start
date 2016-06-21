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
 * 修改Lock的公平性。
 * <p>
 * 我们将修改使用Lock同步代码块食谱示例来使用这个属性，并且观察公平与非公平模式之间的差别。
 * </p>
 *
 * @author xiachuan at 2016/6/21 16:43。
 */

public class FirePrintQueue {

    private final Lock queueLock = new ReentrantLock(true);


    /**
     * 所有线程都创建一个0.1秒的差异，第一需要获取锁的控制权的线程是Thread0，然后是Thread1，以此类推。
     * 当Thread0正在运行第一个由锁 保护的代码块时，有9个线程正在那个代码块上等待执行。
     * 当Thread0释放锁，它需要马上再次获取锁，所以我们有10个线程试图获取这个锁。当启用代码 模式，Lock接口将会选择Thread1，它是在这个锁上等待最长时间的线程。
     * 然后，选择Thread2，然后是Thread3，以此类推。直到所有线 程都通过了这个锁保护的第一个代码块，否则，没有一个线程能执行该锁保护的第二个代码块。
     * 一旦所有线程已经执行完由这个锁保护的第一个代码块，再次轮到Thread0。然后，轮到Thread1，以此类推。
     *
     * @param document
     */
    public void printJob(Object document) {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }


    public static void main(String[] args) {
        FirePrintQueue firePrintQueue = new FirePrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new FireJob(firePrintQueue), "Thread " + i);
        }


        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}

