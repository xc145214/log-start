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
 *
 *  @author xiachuan at 2016/6/30 14:45。
 */

public class ReportProcessor implements Runnable{

    private CompletionService<String> service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        this.end = false;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    @Override
    public void run() {

        while (!end){
            try {
                Future<String> result = service.poll(20, TimeUnit.SECONDS);//获取CompletionService执行的下个已完成任务的Future对象。
                if (result!=null) {
                    String report=result.get();
                    System.out.printf("ReportReceiver: Report Received: %s\n",report);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("ReportSender: End\n");
        }
    }


    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletionService<String> service = new ExecutorCompletionService<String>(executor);

        ReportRequest faceRequest = new ReportRequest("Face",service);
        ReportRequest onlineRequest = new ReportRequest("Online",service);

        Thread faceThread = new Thread(faceRequest);
        Thread onlineThread = new Thread(onlineRequest);

        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);

        System.out.printf("Main: Starting the Threads\n");
        faceThread.start();
        onlineThread.start();
        senderThread.start();


        try {
            System.out.printf("Main: Waiting for the report generators.\n");
                    faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.printf("Main: Shutting down the executor.\n");
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        processor.setEnd(true);
        System.out.println("Main: Ends");


    }
}

