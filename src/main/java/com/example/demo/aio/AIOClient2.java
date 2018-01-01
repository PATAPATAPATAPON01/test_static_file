package com.example.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by PataPon on 2018/1/1.
 */
public class AIOClient2 {

    AsynchronousSocketChannel socketChannel = null;

    public AIOClient2(int port) throws IOException, ExecutionException, InterruptedException {

        socketChannel = AsynchronousSocketChannel.open();

        Future<Void> future = socketChannel.connect(new InetSocketAddress("localhost", port));

        System.out.println(future.get());

    }


    public void write(byte b) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(b);

        buffer.flip();

        socketChannel.write(buffer);


    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        int port = 8787;

        AIOClient2 client = new AIOClient2(port);

        client.write((byte) 29);

    }
}
