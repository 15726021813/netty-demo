package com.xcl.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/17/10:09
 * @Description:
 */
public class NioFileChannel01 {

    public static void main(String[] args) throws IOException {
        String str = "hello world";

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
