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
package com.xc.java7concurrent.chapter4;

import java.util.concurrent.*;

/**
 *  执行者取消一个任务.
 *  <p>
 *      学习如何使用这个方法来取消已经提交给执行者的任务。
 *  </p>
 *
 *  @author xiachuan at 2016/6/29 14:38。
 */

public class CancelTask implements Callable<String>{
    @Override
    public String call() throws Exception {
        while (true){
            System.out.printf("Task: Test\n");
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.
                newCachedThreadPool();
        CancelTask task = new CancelTask();

        System.out.printf("Main: Executing the Task\n");
        Future<String> result=executor.submit(task);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Canceling the Task\n");
        result.cancel(true);

        System.out.printf("Main: Canceled: %s\n",result.isCancelled());
        System.out.printf("Main: Done: %s\n", result.isDone());


        executor.shutdown();
        System.out.printf("Main: The executor has finished\n");
    }


}

