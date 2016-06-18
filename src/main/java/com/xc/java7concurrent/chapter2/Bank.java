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
 *  同步方法。
 *  <p>
 *  实现一个有两个线程访问共同对象的示例。我们将有一个银行帐户和两个线程：其中一个线程将钱转移到帐户而另一个线程将从账户中扣款。在没有同步方法，我们可能得到不正确的结果。同步机制保证了账户的正确。
 *  </p>
 *
 *  @author xiachuan at 2016/6/18 17:17。
 */

public class Bank implements Runnable {

    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {

        for (int i=0;i<100;i++){
            account.subtractAmount(1000);
        }
    }
}

