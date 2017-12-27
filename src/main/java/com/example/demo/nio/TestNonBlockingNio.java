package com.example.demo.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

/**
 * Created by PataPon on 2017/12/24.
 */
public class TestNonBlockingNio {


    @Test
    public void client() throws IOException {


        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9090));

        //切换到费阻塞模式
        socketChannel.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNext()) {

            byteBuffer.put((new Date().toString() + "\n" + scanner.next()).getBytes());

            byteBuffer.flip();

            socketChannel.write(byteBuffer);

        }


        socketChannel.close();


    }

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9799));

        //切换到费阻塞模式
        socketChannel.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


//        byteBuffer.put((new Date().toString() + "\n" + "嗨~").getBytes());
//
//        byteBuffer.flip();
//
//        socketChannel.write(byteBuffer);
//        System.out.println("客户端写入完成");
//        byteBuffer.clear();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            byteBuffer.put((new Date().toString() + "\n" + scanner.next()).getBytes());

            byteBuffer.flip();

            socketChannel.write(byteBuffer);
            System.out.println("客户端写入完成");
            byteBuffer.clear();


            /**
             *
             *  String next = scanner.next();

             buffer.put((new Date().toString() + "\n" + next).getBytes());

             buffer.flip();

             socketChannel.write(buffer);

             buffer.clear();
             */
        }


        socketChannel.close();
    }


    @Test
    public void server() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(9799));


        Selector selector = Selector.open();

        //把通道注册到选择器上，并监听接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        //轮询选择器上准备就绪的事件

        while (selector.select() > 0) {


            //获得所有注册的选择键

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();


            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {  //接收事件准备就绪
//
//                    System.out.println("接收事件准备就绪了");
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);


                    //注册到selector上
                    socketChannel.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {

//                    System.out.println("读事件准备就绪了");

                    SocketChannel channel = (SocketChannel) key.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int len = 0;

                    while ((len = channel.read(buffer)) > 0) {

                        buffer.flip();

                        System.out.println(new String(buffer.array(), 0, len));


                    }

                }
                // selectionKey 要取消掉,不取消就一直有效
                iterator.remove();
            }

        }
    }
}
