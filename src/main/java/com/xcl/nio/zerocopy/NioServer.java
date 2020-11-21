package com.xcl.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/19/15:57
 * @Description:
 */
public class NioServer {

    public static void main(String[] args) throws IOException {

        InetSocketAddress address = new InetSocketAddress(7001);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket socket = serverSocketChannel.socket();

        socket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true){

            SocketChannel accept = serverSocketChannel.accept();
            int readCount = 0;
            while (-1!=readCount){
                try {

                readCount = accept.read(byteBuffer);
                }catch (IOException e){
                    e.printStackTrace();
                }
                byteBuffer.rewind();
            }
        }

    }
}
