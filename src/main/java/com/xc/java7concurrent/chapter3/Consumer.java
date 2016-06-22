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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author xiachuan at 2016/6/22 18:43。
 */

public class Consumer implements Runnable {
    private List<String> buffer;

    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {

        int cycle = 1;

        for (int i = 0; i < 10; i++) {
            System.out.printf("Consumer: Cycle %d\n", cycle);

            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Consumer: " + buffer.size());
            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.println("Consumer: " + message);
                buffer.remove(0);
            }
            cycle++;
        }
    }

    /**
     * 消费者开始时是空白的buffer，然后调用Exchanger来与生产者同步。因为它需要数据来消耗。
     * 生产者也是从空白的buffer开始，然后创建10个字符串，保存到buffer，并使用exchanger与消费者同步。
     * 在这儿，2个线程(生产者和消费者线程)都是在Exchanger里并交换了数据类型，所以当消费者从exchange() 方法返回时，它有10个字符串在buffer内。
     * 当生产者从 exchange() 方法返回时，它有空白的buffer来重新写入。这样的操作会重复10遍。
     * 如你执行例子，你会发现生产者和消费者是如何并发的执行任务和在每个步骤它们是如何交换buffers的。
     * 与其他同步工具一样会发生这种情况，第一个调用 exchange()方法会进入休眠直到其他线程的达到。
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer = new Producer(buffer1, exchanger);
        Consumer consumer = new Consumer(buffer2, exchanger);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);

        threadProducer.start();
        threadConsumer.start();
    }
}

