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

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *  使用非阻塞线程安全的列表.
 *  <p>
 *      学习如何在你的并发应用程序中使用非阻塞列表。
 *      1.大量添加数据到列表
 *      2.在同个列表中，大量删除数据
 *  </p>
 *
 *  @author xiachuan at 2016/6/30 20:22。
 */

public class AddTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name=Thread.currentThread().getName();
        for (int i=0; i<10000; i++){
            list.add(name+": Element "+i);
        }
    }
}

