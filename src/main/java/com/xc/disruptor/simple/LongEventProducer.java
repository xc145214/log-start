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
package com.xc.disruptor.simple;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 *  a source for these events
 *
 *  @author xiachuan at 2016/4/30 14:06。
 */

public class LongEventProducer  {

    private final RingBuffer<LongEvent> ringBuffer ;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb){

        // Grab the next sequence
        long sequence = ringBuffer.next();

        try {
            // Get the entry in the Disruptor
            // for the sequence
            LongEvent event = ringBuffer.get(sequence);

            // Fill with data
            event.set(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }

    }
}

