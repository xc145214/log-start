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
import java.util.List;
import java.util.concurrent.*;

/**
 * 定制ThreadPoolExecutor类。
 * <p>
 * 学习如何覆盖ThreadPoolExecutor类的一些方法，计算你在执行者中执行的任务的执行时间，并且将关于执行者完成它的执行的统计信息写入到控制台。
 * </p>
 *
 * @author xiachuan at 2016/7/2 15:46。
 */

public class MyExecutor extends ThreadPoolExecutor {
    private ConcurrentHashMap<String, Date> startTimes;

    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTimes = new ConcurrentHashMap<>();
    }


    @Override
    public void shutdown() {
        System.out.printf("MyExecutor: Going to shutdown.\n");
        System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());

        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.printf("MyExecutor: Going to immediately shutdown.\n");
        System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
        return super.shutdownNow();
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.printf("MyExecutor: A task is beginning: %s : %s\n", t.getName(), r.hashCode());
        startTimes.put(String.valueOf(r.hashCode()), new Date());
    }


    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> result = (Future<?>) r;
        try {
            System.out.printf("*********************************\n");
            System.out.printf("MyExecutor: A task is finishing.\n");
            System.out.printf("MyExecutor: Result: %s\n", result.get());
            Date startDate = startTimes.remove(String.valueOf(r.
                    hashCode()));
            Date finishDate = new Date();
            long diff = finishDate.getTime() - startDate.getTime();
            System.out.printf("MyExecutor: Duration: %d\n", diff);
            System.out.printf("*********************************\n");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

