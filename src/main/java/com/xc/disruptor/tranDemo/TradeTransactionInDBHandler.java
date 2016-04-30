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
package com.xc.disruptor.tranDemo;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 *  交易处理逻辑.
 *
 *  @author xiachuan at 2016/4/30 14:27。
 */

public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>, WorkHandler<TradeTransaction> {

    Logger LOG = LoggerFactory.getLogger(TradeTransactionInDBHandler.class);
    /**
     * 处理逻辑
     * @param tradeTransaction
     * @param l
     * @param b
     * @throws Exception
     */
    @Override
    public void onEvent(TradeTransaction tradeTransaction, long l, boolean b) throws Exception {

        tradeTransaction.setID(UUID.randomUUID().toString());//简单生成下ID
        System.out.println(tradeTransaction.getID());
        LOG.info(tradeTransaction.toString());
    }


    @Override
    public void onEvent(TradeTransaction tradeTransaction) throws Exception {
        tradeTransaction.setID(UUID.randomUUID().toString());//简单生成下ID
        System.out.println(tradeTransaction.getID());
        LOG.info(tradeTransaction.toString());
    }
}

