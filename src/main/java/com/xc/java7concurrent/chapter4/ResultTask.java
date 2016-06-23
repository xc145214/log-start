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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 运行多个任务并处理所有结果。
 * <p>
 * 学习如何使用这个特性，实现一个示例，执行3个任务，并且当它们完成时将结果打印出来。
 * </p>
 *
 * @author xiachuan at 2016/6/23 13:36。
 */

public class ResultTask implements Callable<Result> {

    private String name;

    public ResultTask(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {

        System.out.printf("%s: Staring\n", this.name);

        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int value = 0;
        for (int i = 0; i < 5; i++) {
            value += (int) (Math.random() * 100);
        }

        Result result = new Result();
        result.setName(name);
        result.setValue(value);

        System.out.println(this.name + ": Ends");
        return result;
    }


    public static void main(String[] args) {
        ExecutorService executor = (ExecutorService) Executors.
                newCachedThreadPool();

        List<ResultTask> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ResultTask task = new ResultTask("task：" + i);
            taskList.add(task);
        }

        List<Future<Result>> resultList = null;

        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Main: Printing the results");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Result> future = resultList.get(i);
            try {
                Result result = future.get();
                System.out.println(result.getName() + ": " + result.
                        getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

