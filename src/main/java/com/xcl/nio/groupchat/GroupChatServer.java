package com.xcl.nio.groupchat;

import jdk.internal.org.objectweb.asm.tree.FieldInsnNode;

import java.io.IOError;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/19/10:34
 * @Description:
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listen;

    public static final int PORT = 6667;


    public GroupChatServer(){
        try {
            selector = Selector.open();
            listen = ServerSocketChannel.open();
            listen.socket().bind(new InetSocketAddress(PORT));
            listen.configureBlocking(false);
            listen.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void listen(){
        try {
            while (true){
                int count = selector.select();
                if (count>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()){
                            SocketChannel socketChannel =  listen.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress()+"上线");

                        }
                        if (key.isReadable()){
                            readData(key);
                        }

                        iterator.remove();

                    }

                }else{
                    System.out.println("等待。。。。");
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
    }

    public void readData(SelectionKey selectionKey){
        SocketChannel socketChannel = null;

        try {

            socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);
            if (count > 0 ){
                String s = new String(buffer.array());
                System.out.println("from 客户端："+s);
                sendInfoToOtherClients(s,socketChannel);

            }

        }catch (IOException e){
            try {
                System.out.println(socketChannel.getRemoteAddress() + "离线了。。。。");
                selectionKey.cancel();
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel socketChannel) throws IOException {
        System.out.println("服务器转发信息中。。。");
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != socketChannel){
                SocketChannel dest = (SocketChannel)targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }

        }

    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();



    }
}
