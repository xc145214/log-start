/*************************************************************************
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *
 *                COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 *    ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *************************************************************************/
package com.xc.nio.state;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;

/**
 * XXXXXXXXXXXXXXXXXXXXX
 *
 * @author xiachuan at 2017/9/1 14:53。
 */

public class EpollClient {

    public static void main(String[] args) {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8000));

            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);

            byte[] buf = new byte[32];
            Random r = new Random();

            int d = 0;
            d = r.nextInt(1000);
            System.out.println(d);
            writeBuffer.put(String.valueOf(d).getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);

            socketChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println(new String(readBuffer.array()));

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            writeBuffer.clear();
            d = r.nextInt(10);
            System.out.println(d);
            writeBuffer.put(String.valueOf(d).getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);

            readBuffer.clear();
            socketChannel.read(readBuffer);
            readBuffer.flip();
            readBuffer.get(buf, 0, readBuffer.remaining());
            System.out.println(new String(buf));

            socketChannel.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

