package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/14/11:38
 * @Description:
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {


        ExecutorService executorService = Executors.newCachedThreadPool();


        ServerSocket serverSocket = new ServerSocket(6666);


        System.out.println("服务器启动了");
        while(true){
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                public void run() {
                    handler(socket);
                }
            });
        }


    }


    public static  void handler(Socket socket){
        try {
          byte[] bytes =   new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if (read!=-1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
