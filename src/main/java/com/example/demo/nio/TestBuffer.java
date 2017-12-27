package com.example.demo.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

/**
 * Created by PataPon on 2017/12/23.
 */
public class TestBuffer {

    @Test
    public void method() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        String s = "abcde";
        byteBuffer.put(s.getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byteBuffer.flip();

        byte bytes[] = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println("after get---");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byteBuffer.rewind();
        System.out.println("after rewind");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("sss");

        byteBuffer.clear();

        System.out.println((char) byteBuffer.get());
    }


    @Test
    public void method2() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String s = "abcde";
        byteBuffer.put(s.getBytes());
        byteBuffer.flip();//切换到读模式
        byte bytes[] = new byte[1024];

        byteBuffer.get(bytes);
        //向下溢出 是缓冲区的数据不能填满字节数组

        System.out.println(new String(bytes, 0, 2));

    }
}
