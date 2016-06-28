package com.xc.java7concurrent.chapter4;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 执行者延迟运行一个任务。
 * <p>
 *     学习如何创建ScheduledThreadPoolExecutor和如何使用它安排任务在指定的时间后执行。
 * </p>
 * Created by Administrator on 2016/6/28.
 */
public class ScheduleTask implements Callable<String> {

    private String name;

    public ScheduleTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {

        System.out.printf("%s: Starting at : %s\n",name,new Date());
        return "Hello, world";
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor
                = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.printf("Main: starting at %s\n",new Date());

        for (int i = 0; i < 5; i++) {
            ScheduleTask task = new ScheduleTask("Task "+i);
            executor.schedule(task,i+1,TimeUnit.SECONDS);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Ends at: %s\n",new Date());
    }
}
