package com.example.demo.nio2;

import org.apache.catalina.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by PataPon on 2017/12/27.
 */
public class NIOServer {


    private ByteBuffer sendBuffer = ByteBuffer.allocate(4096);

    private ByteBuffer receiverBuffer = ByteBuffer.allocate(4096);


    private Selector selector;


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(9797));

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        int times = 0;

        ByteBuffer buffer = ByteBuffer.allocate(4096);

        while (true) {

            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();


            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {

                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();

                    SocketChannel socketChannel = channel.accept();

                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);


                } else if (key.isReadable()) {


                    SocketChannel socketChannel = (SocketChannel) key.channel();


                    int len = 0;
                    buffer.clear();

                    if ((len = socketChannel.read(buffer)) >= 1) {

                        System.out.println("服务端接收客户端数据:\n  " + new String(buffer.array(), 0, len));

                    }

                    socketChannel.register(selector, SelectionKey.OP_WRITE);

                } else if (key.isWritable()) {


                    SocketChannel socketChannel = (SocketChannel) key.channel();


                    buffer.clear();

                    times++;

                    buffer.put(("服务端发送数据给客户端----->  " + times + "\n").getBytes());

                    buffer.flip();

                    socketChannel.write(buffer);


                }

                iterator.remove();


            }
        }
    }

}
