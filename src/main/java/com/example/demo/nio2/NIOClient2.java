package com.example.demo.nio2;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * Created by PataPon on 2017/12/27.
 */
public class NIOClient2 {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        socketChannel.setOption(StandardSocketOptions.SO_RCVBUF,100);
        socketChannel.setOption(StandardSocketOptions.SO_SNDBUF,100);

        //打开选择器
        Selector selector = Selector.open();


        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        socketChannel.connect(new InetSocketAddress(9797));



        int flag = 0;

        ByteBuffer buffer = ByteBuffer.allocate(4096);

        while (true) {

            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();

                if (key.isConnectable()) {

                    SocketChannel channel = (SocketChannel) key.channel();

                    if (channel.isConnectionPending()) {

                        channel.finishConnect();

                        System.out.println("客户端完成了连接工作");

                        buffer.clear();

                        buffer.put("Hello,Server".getBytes());

                        buffer.flip();

                        channel.write(buffer);
                    }


                    channel.register(selector, SelectionKey.OP_READ);


                } else if (key.isReadable()) {


                    SocketChannel channel = (SocketChannel) key.channel();

                    int len = 0;

                    buffer.clear();
                    if ((len = channel.read(buffer)) >= 1) {

                        System.out.println("客户端接收服务端数据:    \n" + new String(buffer.array(), 0, len));

                    }

                    channel.register(selector, SelectionKey.OP_WRITE);

                } else if (key.isWritable()) {

                    SocketChannel channel = (SocketChannel) key.channel();

                    buffer.clear();
                    flag++;

                    buffer.put(("客户端发送数据---->  " + flag + "\n").getBytes());

                    buffer.flip();

                    channel.write(buffer);

                }
                iterator.remove();
            }
        }

    }
}
