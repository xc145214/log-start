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

        /**
         * scheduleAtFixedRate 的四个参数：
         * 你想要周期性执行的任务、
         * 第一次执行任务的延迟时间、
         * 两次执行之间的间隔期间、
         * 第2、3个参数的时间单位。
         */
        ScheduledFuture result = executor.scheduleAtFixedRate(task,1, 2, TimeUnit.SECONDS);

        for (int i=0; i<10; i++){
            System.out.printf("Main: Delay: %d\n",result.
                    getDelay(TimeUnit.MILLISECONDS));
          //Sleep the thread during 500 milliseconds.
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Finished at: %s\n",new Date());
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting at : %s\n",name,new Date());
    }
}
