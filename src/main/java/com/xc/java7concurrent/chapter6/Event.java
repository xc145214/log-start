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

import java.util.Comparator;

/**
 * 用优先级对使用阻塞线程安全的列表排序.
 * <p>
 * 学习如何使用PriorityBlockingQueue类实现一个例子，你将在相同的列表上使用不同的优先级存储大量事件（event），然后检查队列的排序是否是你想要的。
 * </p>
 *
 * @author xiachuan at 2016/7/2 13:40。
 */

public class Event implements Comparable<Event> {

    private int thread;

    private int priority;

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event e) {
        if (this.getPriority() > e.getPriority()) {
            return -1;
        } else if (this.getPriority() < e.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }


}

