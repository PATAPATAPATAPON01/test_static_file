package com.example.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by PataPon on 2018/1/1.
 */
public class MyAIOServer {

    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8989));
        System.out.println("服务端监听端口: 8080 ");

        //接收客户端连接 , 读完之后 再写
        serverSocketChannel.accept("lvjian", new CompletionHandler<AsynchronousSocketChannel, String>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, String attachment) {

                buffer.clear();

                socketChannel.read(buffer, "hha", new CompletionHandler<Integer, String>() {

                    @Override
                    public void completed(Integer result, String attachment) {

                        buffer.flip();

                        System.out.println("服务端接收到客户端数据:  " + new String(buffer.array(), 0, result));

                        buffer.clear();
                        buffer.put("我是服务端,我收到你的消息了".getBytes());
                        buffer.flip();
                        Future<Integer> future = socketChannel.write(buffer);

                        try {
                            future.get();
                            System.out.println("server ends...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failed(Throwable exc, String attachment) {

                    }
                });
            }

            @Override
            public void failed(Throwable exc, String attachment) {

            }
        });

        System.in.read();

    }
}
