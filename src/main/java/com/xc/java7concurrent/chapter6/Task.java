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

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author xiachuan at 2016/7/2 13:47。
 */

public class Task implements Runnable {
    private int id;
    private PriorityBlockingQueue<Event> queue;


    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Event event = new Event(id, i);
            queue.add(event);
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();

        Thread taskThreads[] = new Thread[5];

        for (int i = 0; i < taskThreads.length; i++) {
            Task task = new Task(i, queue);
            taskThreads[i] = new Thread(task);
        }

        for (int i=0; i<taskThreads.length ; i++) {
            taskThreads[i].start();
        }

        for (int i = 0; i < taskThreads.length; i++) {
            try {
                taskThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Queue Size: %d\n", queue.size());
        for (int i = 0; i < taskThreads.length * 1000; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.
                    getThread(), event.getPriority());
        }

        System.out.printf("Main: Queue Size: %d\n", queue.size());
        System.out.printf("Main: End of the program\n");
    }
}

