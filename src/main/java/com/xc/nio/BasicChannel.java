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
package com.xc.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 *  Basic Channel Example
 *
 *  @author xiachuan at 2016/5/3 10:50。
 */

public class BasicChannel {

    Logger LOG = LoggerFactory.getLogger(BasicChannel.class);


    /**
     * read a file。
     * @throws IOException
     */
    public void readFileInChannel () throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("logs/log-start.log","rw");
        FileChannel inChannel = accessFile.getChannel();

        //create a buffer wit h capacity of 48 bytes
        ByteBuffer buffer = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buffer);//read into buffer.
        while (bytesRead != -1){
            System.out.println("Read " + bytesRead);
            LOG.info("Read: {}",bytesRead);
            buffer.flip();//make buffer ready for read

            while(buffer.hasRemaining()){
                System.out.print((char) buffer.get());  //read 1 byte at a time
            }

            buffer.clear();// make buffer ready for writing
            bytesRead = inChannel.read(buffer);
        }
        accessFile.close();
    }

    /**
     * copy  from a file。
     * @throws IOException
     */
    public void transferFrom() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("logs/log-start.log","rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("logs/to1.log","rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }

    /**
     * copy to a file
     * @throws IOException
     */
    public void transferTo() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("logs/log-start.log","rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("logs/to2.log","rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }

   public void FileChannel()throws IOException{
       RandomAccessFile aFile = new RandomAccessFile("logs/nio-data.txt", "rw");
       FileChannel channel = aFile.getChannel();

       //write data
       String newData = "New String to write to file..." + System.currentTimeMillis();

       ByteBuffer buf = ByteBuffer.allocate(48);
       buf.clear();
       buf.put(newData.getBytes());

       buf.flip();

       while(buf.hasRemaining()) {
           channel.write(buf);
       }
       System.out.println(channel.size());

       //关闭channel
       channel.close();
   }


    public void socketChannel() throws IOException {
       //打开channel
        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://127.0.0.1", 9999));

        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while(buf.hasRemaining()) {
            socketChannel.write(buf);
        }


        //关闭channel
        socketChannel.close();
    }



    public void serverSocket() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        while (true){
            SocketChannel socketChannel =  serverSocketChannel.accept();

            ByteBuffer buffer = ByteBuffer.allocate(48);
            int bytesRead = socketChannel.read(buffer);
            while (bytesRead != -1){
                System.out.println("Read " + bytesRead);
                LOG.info("Read: {}",bytesRead);
                buffer.flip();//make buffer ready for read

                while(buffer.hasRemaining()){
                    System.out.print((char) buffer.get());  //read 1 byte at a time
                }

                buffer.clear();// make buffer ready for writing
                bytesRead = socketChannel.read(buffer);
            }
        }
    }

}

