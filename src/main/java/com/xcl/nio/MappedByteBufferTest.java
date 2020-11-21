package com.xcl.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/18/8:52
 * @Description:
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {

        RandomAccessFile rw = new RandomAccessFile("1.txt", "rw");

        FileChannel channel = rw.getChannel();

        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0,(byte)'H');
        map.put(3,(byte)'2');

        rw.close();

        System.out.println("修改成功");


    }

}
