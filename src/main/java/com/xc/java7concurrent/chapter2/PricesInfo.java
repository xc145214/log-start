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
package com.xc.java7concurrent.chapter2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读/写锁同步数据访问.
 * <p>
 * ReadWriteLock接口实现一个程序，使用它来控制访问一个存储两个产品价格的对象。
 * </p>
 *
 * @author xiachuan at 2016/6/21 16:13。
 */

public class PricesInfo {

    private double price1;
    private double price2;

    private ReadWriteLock lock;

    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }


    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }


    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }


    public static void main(String[] args) {

        PricesInfo pricesInfo = new PricesInfo();

        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }

        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);


        for (int i = 0; i < 5; i++) {
            threadsReader[i].start();
        }
        threadWriter.start();


    }

}

