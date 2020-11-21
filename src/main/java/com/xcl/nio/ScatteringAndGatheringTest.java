package com.xcl.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/18/9:00
 * @Description:
 */
public class ScatteringAndGatheringTest {
    /**
     * scattering  将数据写入到buffer 可以采用buffer数组  依次写入 [分散]
     * gatering    从buffer读数据时， 可以采用buffer数组  依次读
     * @param args
     */
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        SocketChannel accept = serverSocketChannel.accept();
        int messageLength = 8;
        while (true){
            int byteRead = 0;
            while (byteRead < messageLength){
                long read = accept.read(byteBuffers);
                byteRead += read;
                System.out.println("byteRead = "+byteRead);
                Arrays.asList(byteBuffers).stream().map(buffer-> "postion="+buffer.position()+", limit"+buffer.limit()).forEach(System.out::println);

            }
            Arrays.asList(byteBuffers).forEach(buffer-> System.out.println(new String(buffer.array())));
            Arrays.asList(byteBuffers).forEach(buffer-> buffer.flip());

            long bytewrite = 0;
            while (bytewrite < messageLength){
                long write = accept.write(byteBuffers);
                bytewrite+= write;
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });

            System.out.println("byteRead:="+byteRead+",  byteWrite="+bytewrite+", messageLength="+messageLength);
        }


    }
}
