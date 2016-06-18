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

/**
 *  模拟公司，增加余额。
 *
 *  @author xiachuan at 2016/6/18 17:19。
 */

public class Company implements Runnable{

    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {

        for (int i = 0; i <100 ; i++) {
            account.addAmount(1000);
        }
    }


    public static void main(String[] args) {

        Account account = new Account();
        account.setBalance(1000);


        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        System.out.printf("Account : Initial Balance: %f\n", account.getBalance());

        companyThread.start();
        bankThread.start();

        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %f\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

