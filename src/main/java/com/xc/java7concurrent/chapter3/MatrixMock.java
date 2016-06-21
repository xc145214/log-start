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
package com.xc.java7concurrent.chapter3;

import java.util.Random;

/**
 * 在同一个点同步任务.
 * <p>
 * 学习如何使用 CyclicBarrier 类来让一组线程在一个确定点同步。
 * 你也将使用 Runnable 对象，它将会在全部线程都到达确定点后被执行。
 * 在这个例子里，你将在数字矩阵中查找一个数字。矩阵会被分成多个子集（使用divide 和 conquer 技术），所以每个线程会在一个子集中查找那个数字。
 * 一旦全部行程运行结束，会有一个最终任务来统一他们的结果。
 * </p>
 *
 * @author xiachuan at 2016/6/21 19:50。
 */

public class MatrixMock {

    private int data[][];

    public MatrixMock(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }

        System.out.printf("Mock: There are %d ocurrences of number in generated data.\n", counter, number);
    }


    public int[] getRow(int row) {
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }

}

