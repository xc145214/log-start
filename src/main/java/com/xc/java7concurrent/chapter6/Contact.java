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

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 使用线程安全的NavigableMap.
 * <p>
 * 学习如何使用ConcurrentSkipListMap类来实现一个通讯录的map。
 * </p>
 *
 * @author xiachuan at 2016/7/2 14:28。
 */

public class Contact {

    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public static void main(String[] args) {
        ConcurrentSkipListMap<String, Contact> map;
        map = new ConcurrentSkipListMap<>();

        Thread threads[] = new Thread[25];
        int counter = 0;

        for (char i = 'A'; i < 'Z'; i++) {
            SkipTask task = new SkipTask(map, String.valueOf(i));
            threads[counter] = new Thread(task);
            threads[counter].start();
            counter++;
        }

        for (int i = 0; i < 25; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the map: %d\n", map.size());
        Map.Entry<String, Contact> element;
        Contact contact;
        element = map.firstEntry();
        contact = element.getValue();
        System.out.printf("Main: First Entry: %s: %s\n", contact.
                getName(), contact.getPhone());

        element = map.lastEntry();
        contact = element.getValue();
        System.out.printf("Main: Last Entry: %s: %s\n", contact.
                getName(), contact.getPhone());

        System.out.printf("Main: Submap from A1996 to B1002: \n");
        ConcurrentNavigableMap<String, Contact> submap = map.
                subMap("A1996", "B1002");
        do {
            element = submap.pollFirstEntry();
            if (element != null) {
                contact = element.getValue();
                System.out.printf("%s: %s\n", contact.getName(), contact.
                        getPhone());
            }
        } while (element != null);
    }

}

