package com.example.demo.nio;

import org.junit.Test;
import org.luaj.vm2.ast.Str;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by PataPon on 2017/12/24.
 */
public class TestBlockingIO2 {

    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9090));

        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/PataPon/IdeaProjects/test_static_file/src/main/resources/a.txt"), StandardOpenOption.READ);

        System.out.println(1);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while ((fileChannel.read(byteBuffer) != -1)) {

            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();

        }


        int len = 0;

        while ((len = socketChannel.read(byteBuffer)) != -1) {

            System.out.println(byteBuffer.position());
            System.out.println(byteBuffer.limit());

            byteBuffer.flip();

            System.out.println("====================");

            System.out.println(byteBuffer.position());
            System.out.println(byteBuffer.limit());
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }

        fileChannel.close();
        socketChannel.close();

    }


    @Test
    public void server() throws IOException, InterruptedException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        //获得客户端连接
        while (true) {

            SocketChannel socketChannel = serverSocketChannel.accept();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

//            while (socketChannel.read(byteBuffer) != -1) {
//
//                byteBuffer.flip();
//                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
//                byteBuffer.clear();
//            }


            byteBuffer.put("服务端接受成功".getBytes());

            byteBuffer.flip();

            socketChannel.write(byteBuffer);
            System.out.println("服务端发送完毕");
//            socketChannel.close();
        }
    }
}
