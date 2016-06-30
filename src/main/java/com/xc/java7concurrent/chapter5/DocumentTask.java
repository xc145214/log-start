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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * @author xiachuan at 2016/6/30 17:40。
 */

public class DocumentTask extends RecursiveTask<Integer> {

    private String document[][];
    private int start, end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {

        int result = 0;
        if (end - start < 10) {
            result = processLines(document, start, end, word);
        } else {
            int mid = (start + end) / 2;
            DocumentTask task1 = new DocumentTask(document, start, mid, word);
            DocumentTask task2 = new DocumentTask(document, mid, end, word);
            invokeAll(task1, task2);
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    private Integer processLines(String[][] document, int start, int
            end, String word) {
        List<LineTask> tasks = new ArrayList<LineTask>();
        for (int i = start; i < end; i++) {
            LineTask task = new LineTask(document[i], 0, document[i].
                    length, word);
            tasks.add(task);
        }
        invokeAll(tasks);
        int result = 0;
        for (int i = 0; i < tasks.size(); i++) {
            LineTask task = tasks.get(i);
            try {
                result = result + task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return new Integer(result);

    }

    private Integer groupResults(Integer number1, Integer number2) {
        Integer result;
        result = number1 + number2;
        return result;
    }
}

