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

/**
 * 在Lock中使用多个条件。
 * <p>
 * 在这个指南中，你将学习如何通过使用锁和条件来实现生产者与消费者问题。
 * </p>
 *
 * @author xiachuan at 2016/6/21 16:55。
 */

public class FileMock {

    private String content[];
    private int index;


    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder buffer = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) Math.random() * 255;
                buffer.append((char) indice);
            }
            content[i] = buffer.toString();
        }

        index = 0;
    }

    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLine() {
        if (this.hasMoreLines()) {
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }


    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);

        Buffer buffer = new Buffer(20);

        MockProducer producer = new MockProducer(mock, buffer);
        Thread threadProducer = new Thread(producer, "Producer");

        MockConsumer consumers[] = new MockConsumer[3];
        Thread threadConsumers[] = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new MockConsumer(buffer);
            threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
        }


        threadProducer.start();
        for (int i = 0; i < 3; i++) {
            threadConsumers[i].start();
        }


    }
}

