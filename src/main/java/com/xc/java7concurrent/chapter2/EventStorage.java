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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 同步代码块中使用条件。
 * <p/>
 * <p>
 * 通过使用synchronized关键字和wait()和notify(),notifyAll()方法实现生产者消费者问题.
 * </p>
 *
 * @author xiachuan at 2016/6/21 15:21。
 */

public class EventStorage {

    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }


    /**
     * 实现synchronized方法set()，用来在storage上存储一个事件。
     * 首先，检查storage是否已满。如果满了，调用wait()方 法，直到storage有空的空间。
     * 在方法的尾部，我们调用notifyAll()方法来唤醒，所有在wait()方法上睡眠的线程。
     */
    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.offer(new Date());
        System.out.printf("Set: %d \n", storage.size());
        notifyAll();
    }

    /**
     * 实现synchronized方法get()，用来在storage上获取一个事件。
     * 首先，检查storage是否有事件。如果没有，调用wait()方 法直到，storage有一些事件，在方法的尾部，我们调用notifyAll()方法来唤醒，所有在wait()方法上睡眠的线程。
     */
    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d: %s \n", storage.
                size(), ((LinkedList<?>) storage).poll());
        notifyAll();
    }


    public static void main(String[] args) {
        EventStorage storage = new EventStorage();

        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);

        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);

        thread2.start();
        thread1.start();
    }
}

