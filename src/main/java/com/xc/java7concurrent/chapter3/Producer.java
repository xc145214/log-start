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

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 在并发任务间交换数据。
 * <p>
 * 学习如何使用 Exchanger 类来解决只有一个生产者和一个消费者的生产者和消费者问题。
 * </p>
 *
 * @author xiachuan at 2016/6/22 16:31。
 */

public class Producer implements Runnable {

    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {

        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Producer: Cycle %d\n", cycle);

            for (int j = 0; j < 10; j++) {
                String message = "Event " + ((i * 10) + j);
                System.out.printf("Producer: %s\n", message);
                buffer.add(message);
            }

            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer: " + buffer.size());
            cycle++;
        }
    }
}

