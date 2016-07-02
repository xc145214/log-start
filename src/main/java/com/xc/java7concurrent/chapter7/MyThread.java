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
package com.xc.java7concurrent.chapter7;

import java.util.Date;

/**
 *  实现ThreadFactory接口生成自定义的线程.
 *  <p>
 *      继承Thread类，以添加新功能，并且你将实现一个线程工厂来创建这个新类的线程。
 *  </p>
 *
 *  @author xiachuan at 2016/7/2 17:05。
 */

public class MyThread extends Thread {

        private Date creationDate;

        private Date startDate;

    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate();
    }


    public void setCreationDate(){
        creationDate=new Date();
    }


    @Override
    public void run() {
        setStartDate();
        super.run();
        setFinishDate();
    }

    public void setStartDate() {
        startDate=new Date();
    }

    public void setFinishDate() {
        finishDate=new Date();
    }

    public long getExecutionTime() {
        return finishDate.getTime()-startDate.getTime();
    }


    @Override
    public String toString() {

        StringBuilder buffer=new StringBuilder();
        buffer.append(getName());
        buffer.append(": ");
        buffer.append(" Creation Date: ");
        buffer.append(creationDate);
        buffer.append(" : Running time: ");
        buffer.append(getExecutionTime());
        buffer.append(" Milliseconds.");
        return buffer.toString();
    }
}

