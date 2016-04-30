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
package com.xc.disruptor.tranJmsDemo;

import com.lmax.disruptor.EventHandler;
import com.xc.disruptor.tranDemo.TradeTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * save消费者。
 *
 * @author xiachuan at 2016/4/30 15:20。
 */

public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {

    Logger LOG = LoggerFactory.getLogger(TradeTransactionVasConsumer.class);
    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        //todo save
        LOG.info("event {} has saved",event);

    }

}