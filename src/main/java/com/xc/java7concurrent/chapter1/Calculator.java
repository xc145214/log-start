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
package com.xc.java7concurrent.chapter1;

/**
 * 实现 Runnable 接口穿件线程。
 * <p>
 *     使用实现#Runable接口来制作一个简单的程序，它能创建和运行10个线程。每一个线程能计算和输出1-10以内的乘法表。
 * </p>
 *
 * @author xiachuan at 2016/5/19 20:08。
 */

public class Calculator implements Runnable {
    public Calculator(int number) {
        this.number = number;
    }

    public Calculator() {
    }

    private int number;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);

        }
    }


    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }

    }
}

