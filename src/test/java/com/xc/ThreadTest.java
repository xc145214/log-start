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
package com.xc;

import com.xc.concurrent.guarded.Consumer;
import com.xc.concurrent.guarded.Drop;
import com.xc.concurrent.guarded.Producer;
import com.xc.concurrent.sync.Counter;
import com.xc.concurrent.thread.HelloRunnable;
import com.xc.concurrent.thread.HelloThread;
import com.xc.concurrent.thread.SleepMessages;
import junit.framework.TestCase;

/**
 *  thread 测试类。
 *
 *  @author xiachuan at 2016/4/29 15:14。
 */

public class ThreadTest extends TestCase {


    public void testRun() throws Exception {
        new Thread(new HelloRunnable()).start();
        new HelloThread().start();
    }

    public void testInterrupt1() throws Exception {
        new SleepMessages().go();
    }

    public void testCounter() throws Exception {
        Counter counter = new Counter();
    }

}

