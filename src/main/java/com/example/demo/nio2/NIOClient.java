package com.example.demo.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by PataPon on 2017/12/27.
 */
public class NIOClient {


    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9797));

        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(2048);

        buffer.put("我是客户端".getBytes());

        buffer.flip();


        socketChannel.write(buffer);

        System.out.println("客户端发送完毕");

        buffer.clear();

        int len = 0;

        while (true) {


            while ((len = socketChannel.read(buffer)) != -1) {

                buffer.flip();

                System.out.println(new String(buffer.array(), 0, len));

            }
        }

    }
}
