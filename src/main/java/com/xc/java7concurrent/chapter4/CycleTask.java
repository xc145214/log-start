package com.xc.java7concurrent.chapter4;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 执行者周期性地运行一个任务.
 * <p>
 *     学习如何通过使用这个类的功能来安排一个周期性任务。
 * </p>
 *
 * Created by Administrator on 2016/6/28.
 */
public class CycleTask implements Runnable {

    private String name;

    public CycleTask(String name) {
        this.name = name;
    }


    public String call() throws Exception {
        System.out.printf("%s: Starting at : %s\n",name,new Date());
        return "Hello, world";
    }


    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor
                = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n",new Date());
        CycleTask task = new CycleTask("Task");

        ScheduledFuture result = executor.scheduleAtFixedRate(task,1, 2, TimeUnit.SECONDS);


    }

    @Override
    public void run() {

    }
}
