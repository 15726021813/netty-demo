package com.xcl.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/19/8:35
 * @Description:
 */
public class NioServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        serverSocketChannel.configureBlocking(false);


//        注册       保持为
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册数量" +selector.keys().size());

        while (true){
            if (selector.select(1000) == 0){
                System.out.println("服务器等待了一秒钟，无连接");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey>  keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()){
                    SocketChannel socketChannel= serverSocketChannel.accept();
                    System.out.println("客户端连接成功");
                     socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("注册数量" +selector.keys().size());

                }
                if (key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();

                    channel.read(byteBuffer);
                    System.out.println("From   客户端 "+new String(byteBuffer.array()));

                }

                keyIterator.remove();
            }
        }



    }
}
