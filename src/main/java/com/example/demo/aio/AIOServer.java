package com.example.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by PataPon on 2018/1/1.
 */
public class AIOServer {


    public AIOServer(int port) throws IOException {


        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(port));

        serverSocketChannel.accept("lvjian", new CompletionHandler<AsynchronousSocketChannel, String>() {

            @Override
            public void completed(AsynchronousSocketChannel ch, String attachment) {

                serverSocketChannel.accept(null, this); //接收下一个连接

                handler(ch);
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("异步IO失败");
            }
        });
    }

    private void handler(AsynchronousSocketChannel ch) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            ch.read(buffer).get();

        } catch (Exception e) {
            e.printStackTrace();
        }

        buffer.flip();
        System.out.println("服务端接收:  " + buffer.get());
    }

    public static void main(String[] args) throws IOException {

        int port = 8787;

        AIOServer aioServer = new AIOServer(port);

        System.out.println("服务端监听端口:  " + port);


        System.in.read();
    }
}
