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

import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 *
 *  @author xiachuan at 2016/7/2 14:30。
 */

public class SkipTask implements Runnable{

    private String id;
    private ConcurrentSkipListMap<String,Contact> map;

    public SkipTask( ConcurrentSkipListMap<String, Contact> map,String id) {
        this.id = id;
        this.map = map;
    }

    @Override
    public void run() {
        for (int i=0; i<1000; i++) {
            Contact contact=new Contact(id, String.valueOf(i+1000));
            map.put(id+contact.getPhone(), contact);
        }
    }
}

