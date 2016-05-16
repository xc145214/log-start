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
package com.xc;

import com.xc.nio.BasicChannel;
import junit.framework.TestCase;

/**
 *  sample nio test
 *
 *  @author xiachuan at 2016/5/3 10:58。
 */

public class NioTest extends TestCase {

    BasicChannel basicChannel;


    @Override
    public void setUp() throws Exception {
        basicChannel = new BasicChannel();
    }


    public void testChannel() throws Exception {
        basicChannel.readFileInChannel();
    }

    public void testCopyFrom() throws Exception {
        basicChannel.transferFrom();
    }

    public void testCopyTo() throws Exception {
        basicChannel.transferTo();
    }

    public void testFile() throws Exception {
        basicChannel.FileChannel();
    }


    public void testSocket() throws Exception {
        basicChannel.socketChannel();
    }

    public void testServer() throws Exception {
        basicChannel.serverSocket();
    }
}

