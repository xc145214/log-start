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
package com.xc.java7concurrent.chapter5;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *  加入任务的结果.
 *  <p>
 *      学习如何使用Fork/Join框架解决这种问题，开发一个在文档中查找单词的应用程序。你将会实现以下两种任务类型：
 *          一个文档任务，将在文档中的行集合中查找一个单词。
 *          一个行任务，将在文档的一部分数据中查找一个单词。
 *      所有任务将返回单词在文档的一部分中或行中出现的次数。
 *  </p>
 *
 *  @author xiachuan at 2016/6/30 17:32。
 */

public class Document {

    private String words[]={"the","hello","goodbye","packt", "java","thread","pool","random","class","main"};

    public String[][] generateDocument(int numLines, int numWords,
                                       String word){
        int counter=0;
        String document[][]=new String[numLines][numWords];
        Random random=new Random();
        for (int i=0; i<numLines; i++){
            for (int j=0; j<numWords; j++) {
                int index=random.nextInt(words.length);
                document[i][j]=words[index];
                if (document[i][j].equals(word)){
                    counter++;
                }
            }
        }

        System.out.println("DocumentMock: The word appears "+
                counter+" times in the document");
        return document;
    }


    public static void main(String[] args) {
        Document mock=new Document();
        String[][] document=mock.generateDocument(100, 1000, "the");

        DocumentTask task=new DocumentTask(document, 0, 100, "the");
        ForkJoinPool pool=new ForkJoinPool();
        pool.execute(task);

        do {
            System.out.printf("***************************************** *\n");
                    System.out.printf("Main: Parallelism: %d\n",pool.
                            getParallelism());
            System.out.printf("Main: Active Threads: %d\n",pool.
                    getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n",pool.
                    getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n",pool.
                    getStealCount());
            System.out.printf("***************************************** *\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());


        pool.shutdown();

        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.printf("Main: The word appears %d in the document",task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}

