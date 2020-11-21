package com.xcl.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/17/10:27
 * @Description:
 */
public class NioFileChannel02 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\file01.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer b = ByteBuffer.allocate(1024);
        int read = channel.read(b);
        System.out.println(new String(b.array()));
        fileInputStream.close();


    }
}
