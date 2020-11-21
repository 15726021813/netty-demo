package com.xcl.nio;

import java.nio.IntBuffer;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/17/8:50
 * @Description:
 */
public class BasicBuffer {

    public static void main(String[] args) {


        IntBuffer  intBuffer = IntBuffer.allocate(5);


//        intBuffer.put(10);
//        intBuffer.put(11);
//        intBuffer.put(12);
//        intBuffer.put(13);
//        intBuffer.put(14);
//        intBuffer.put(15);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }




    }
}
