package com.example.demo.nio;

import com.example.demo.testPic.SocketClient;
import org.junit.Test;

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
public class TestBlockingIO {

    @Test
    public void client() throws IOException {


        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9898));
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/PataPon/IdeaProjects/test_static_file/src/main/resources/a.txt"), StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while ((fileChannel.read(byteBuffer) != -1)) {

            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        fileChannel.close();
        socketChannel.close();

    }


    @Test
    public void server() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));

        //获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while ((socketChannel.read(byteBuffer) != -1)) {

            byteBuffer.flip();

            System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));

            byteBuffer.clear();

        }

        serverSocketChannel.close();
        socketChannel.close();

    }
}
