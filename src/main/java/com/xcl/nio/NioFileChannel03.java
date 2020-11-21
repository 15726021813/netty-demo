package com.xcl.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/17/10:33
 * @Description:
 */
public class NioFileChannel03 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel channel1 = fileInputStream.getChannel();


        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        while (true){
            allocate.clear();
            int read = channel1.read(allocate);
            if (read == -1 ){
                break;
            }
            allocate.flip();
            channel.write(allocate);

        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
