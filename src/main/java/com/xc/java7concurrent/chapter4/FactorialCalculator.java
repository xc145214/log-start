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
import java.util.Random;
import java.util.concurrent.*;

/**
 * 执行者执行返回结果的任务。
 * <p>
 * 学习如何实现返回结果的任务，并在执行者中运行它们。
 * 使用FactorialCalculator类实现了Callable接口，并参数化为Integer类型作为结果类型。
 * 因此，Integer就作为call()方法的返回类型。
 * </p>
 *
 * @author xiachuan at 2016/6/23 11:05。
 */

public class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;

        if ((number == 0) || (number == 1)) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf("%s: %d\n", Thread.currentThread().
                getName(), result);

        return result;
    }

    /**
     * 用submit()方法提交一个Callable对象给执行者执行。这个方法接收Callable对象参数，并且返回一个Future对象，你可以以这两个目标来使用它：
     *  1. 你可以控制任务的状态：你可以取消任务，检查任务是否已经完成。基于这个目的，你已经使用isDone()方法来检查任务是否已经完成。
     *  2. 你可以获取call()方法返回的结果。基于这个目的，你已经使用了get()方法。这个方法会等待，直到Callable对象完成call()方法的执 行，并且返回它的结果。
     *  如果线程在get()方法上等待结果时被中断，它将抛出InterruptedException异常。
     *  如果call()方法抛出 异常，这个方法会抛出ExecutionException异常。
     *
     *  当你调用Future对象的get()方法，并且这个对象控制的任务未完成，这个方法会阻塞直到任务完成。
     *  Future接口提供其他版本的get()方法：get(long timeout, TimeUnit unit)：这个版本的get方法，如果任务的结果不可用，等待它在指定的时间内。如果时间超时，并且结果不可用，这个方法返回null值。
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.
                newFixedThreadPool(2);

        List<Future<Integer>> resultList = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);

            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }

        do {
            System.out.printf("Main: Number of Completed Tasks: %d\n", executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main: Task %d: %s\n", i, result.
                        isDone());
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } while (executor.getCompletedTaskCount() < resultList.size());

        System.out.printf("Main: Results\n");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);

            Integer number = null;
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.printf("Main: Task %d: %d\n", i, number);
        }


        executor.shutdown();

    }
}

