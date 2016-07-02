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
package com.xc.java7concurrent.chapter6;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 *  学习如何在你的并发应用程序中使用阻塞的列表。
 *
 *  @author xiachuan at 2016/6/30 20:29。
 */

public class Client  implements Runnable{

    private LinkedBlockingDeque requestList;

    public Client(LinkedBlockingDeque requestList) {
        this.requestList = requestList;
    }

    @Override
    public void run() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<5; j++) {
                StringBuilder request=new StringBuilder();
                request.append(i);
                request.append(":");
                request.append(j);
                try {
                    requestList.put(request.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Client: %s at %s.\n",request,new
                        Date());
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Client: End.\n");
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> list= new LinkedBlockingDeque<>();

        Client client = new Client(list);
        Thread thread = new Thread(client);

        thread.start();

        for (int i=0; i<5 ; i++) {
            for (int j=0; j<3; j++) {
                String request=list.take();
                System.out.printf("Main: Request: %s at %s. Size: %d\n",request,new Date(),list.size());
            }
            TimeUnit.MILLISECONDS.sleep(300);
        }

        System.out.printf("Main: End of the program.\n");
    }
}

