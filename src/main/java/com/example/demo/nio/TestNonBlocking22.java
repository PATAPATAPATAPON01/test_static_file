package com.example.demo.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by PataPon on 2017/12/24.
 */
public class TestNonBlocking22 {


    @Test
    public void client() throws IOException {

//        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9898));
//        socketChannel.configureBlocking(false);
//
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//        buffer.put((new Date().toString().getBytes()));
//
//        buffer.flip();
//        socketChannel.write(buffer);
//
//        socketChannel.close();
    }


    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9898));

        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            String next = scanner.next();

            buffer.put((new Date().toString() + "\n" + next).getBytes());

            buffer.flip();

            socketChannel.write(buffer);

            buffer.clear();

        }

        socketChannel.close();
    }

    @Test
    public void server() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(9898));


        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (selector.select() > 0) {


            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {

                SelectionKey sk = it.next();
                if (sk.isAcceptable()) {

                    SocketChannel sChannel = serverSocketChannel.accept();
                    sChannel.configureBlocking(false);

                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {


                    SocketChannel channel = (SocketChannel) sk.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int len = 0;

                    while ((len = channel.read(buffer)) > 0) {

                        buffer.flip();

                        System.out.println(new String(buffer.array(), 0, len));


                    }

                }

                it.remove();
            }
        }

    }
}
