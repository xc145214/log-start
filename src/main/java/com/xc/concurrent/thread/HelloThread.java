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
package com.xc.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  继承Thread类。
 *
 *  @author xiachuan at 2016/4/29 15:13。
 */

public class HelloThread extends Thread {
    Logger LOG = LoggerFactory.getLogger(HelloThread.class);

    @Override
    public void run() {
        LOG.info("hello from a thread implements Runnable!");
    }

    public static void main(String[] args) {
        new HelloThread().start();
    }
}

