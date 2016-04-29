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
 *  在Thread中有两个不同的sleep()方法，一个使用毫秒表示休眠的时间，而另一个是用纳秒
 *
 *  @author xiachuan at 2016/4/29 15:19。
 */

public class SleepMessages {

    Logger LOG = LoggerFactory.getLogger(SleepMessages.class);

    /**
     * 暂停一个线程。
     *
     * @throws InterruptedException
     */
    public void go() throws InterruptedException {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            //Pause for 4 seconds
            Thread.sleep(4000);
            //Print a message
            System.out.println(importantInfo[i]);

            LOG.info(importantInfo[i]);
        }
    }

    /**
     * 支持中断。
     */
    public void runMethod(){
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            // Pause for 4 seconds
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // We've been interrupted: no more messages.
                return;
            }
            // Print a message
            System.out.println(importantInfo[i]);

            LOG.info(importantInfo[i]);
        }
    }

    public static void main(String args[])
            throws InterruptedException {
        new SleepMessages().go();
    }
}

