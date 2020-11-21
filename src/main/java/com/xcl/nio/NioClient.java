package com.xcl.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/19/9:10
 * @Description:
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();


        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);


        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞 ， 可以做其他工作");
            }

            String  s = "hello world";
            ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
            socketChannel.write(wrap);
            System.in.read();

        }
    }
}
