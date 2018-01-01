package com.example.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by PataPon on 2018/1/1.
 */
public class MyNioClient {


    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(2048);
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost", 8989), "", new CompletionHandler<Void, String>() {
            @Override
            public void completed(Void result, String attachment) {

                buffer.clear();

                buffer.put("我是客户端".getBytes());

                buffer.flip();

                socketChannel.write(buffer, "", new CompletionHandler<Integer, String>() {
                    @Override
                    public void completed(Integer result, String attachment) {

                        System.out.println("客户端发送数据完成..." + result);
                        buffer.clear();

                        Future<Integer> future = socketChannel.read(buffer);

                        try {
                            future.get();

                            System.out.println(new String(buffer.array(), 0, future.get()));
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
