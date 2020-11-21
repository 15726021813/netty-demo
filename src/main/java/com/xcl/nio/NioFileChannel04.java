package com.xcl.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/17/13:40
 * @Description:
 */
public class NioFileChannel04 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\a2.jpg");


        FileChannel channel = fileInputStream.getChannel();
        FileChannel channel1 = fileOutputStream.getChannel();

        channel1.transferFrom(channel,0,channel.size());

        channel.close();
        channel1.close();

        fileInputStream.close();
        fileOutputStream.close();




    }
}
