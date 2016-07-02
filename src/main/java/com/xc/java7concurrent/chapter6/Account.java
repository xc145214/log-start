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

import java.util.concurrent.atomic.AtomicLong;

/**
 *  使用原子变量。
 *  <p>
 *      学习怎样使用原子变量实现一个银行账户和2个不同的任务：一个存钱到账户和另一个从账户提取钱。在例子的实现中，你将使用 AtomicLong 类。
 *  </p>
 *
 *  @author xiachuan at 2016/7/2 14:53。
 */

public class Account {
    private AtomicLong balance;

    public Account() {
        balance= new AtomicLong();
    }

    public Long getBalance() {
        return balance.get();
    }

    public void setBalance(Long balance) {
        this.balance.set(balance);
    }

    public void  addAmount(Long amount){
        this.balance.getAndAdd(amount);
    }

    public void subtractAmount(Long amount){
        this.balance.getAndAdd(-amount);
    }

    public static void main(String[] args) {
        Account account=new Account();
        account.setBalance(1000l);

        Company company=new Company(account);
        Thread companyThread=new Thread(company);

        Bank bank=new Bank(account);
        Thread bankThread=new Thread(bank);

        System.out.printf("Account : Initial Balance: %d\n",account.
                getBalance());

        companyThread.start();
        bankThread.start();

        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %d\n",account.
                    getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

