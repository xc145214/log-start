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

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *   创建一个线程执行者.
 *  <p>
 *      学习如何使用这两种操作来实现一个web服务器的示例，这个web服务器用来处理各种客户端请求。
 *  </p>
 * @author xiachuan at 2016/6/22 19:01。
 */

public class Server {

    private ThreadPoolExecutor executor;

    public Server() {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void executeTask(Task task) {
        System.out.printf("Server: A new task has arrived\n");

        executor.execute(task);

        System.out.printf("Server: Pool Size: %d\n", executor.
                getPoolSize());
        System.out.printf("Server: Active Count: %d\n", executor.
                getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n", executor.
                getCompletedTaskCount());

    }


    public void endServer() {
        executor.shutdown();
    }


    public static void main(String[] args) {
        Server server = new Server();

        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);

            server.executeTask(task);
        }
        server.endServer();
    }
}
