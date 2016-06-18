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
package com.xc.java7concurrent.chapter1;

/**
 *  线程的中断。
 * <p>
 *     开发一个程序，它创建线程，然后在5秒之后，它会使用中断机制来强制结束线程。
 * </p>
 *  @author xiachuan at 2016/6/17 14:57。
 */

public class PrimeGenerator  extends Thread{


    @Override
    public void run() {
        long number = 1l;
        while (true){
            if(isPrime(number)){
                System.out.printf("Number %d is Prime",number);
                System.out.println();
            }
            if (isInterrupted()) {
                System.out.printf("The Prime Generator has been Interrupted");
                System.out.println();
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number){
        if(number<=2){
            return  true;
        }
        for (long i =2 ;i<number;i++){
            if((number %i)==0){
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        task.interrupt();
    }


}

