package com.example.demo.nio;

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
public class TestBlockingNio3 {

    @Test
    public void client() throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9090));


        FileChannel inChannel = FileChannel.open(Paths.get("/Users/PataPon/IdeaProjects/test_static_file/src/main/resources/a.txt"), StandardOpenOption.READ);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (inChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        //此处一定要shutdown 不然服务器会阻塞在读 不知道客户端有没有写完毕
        socketChannel.shutdownOutput();
        int len = 0;

        while ((len = socketChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, len));
            buf.clear();
        }

        inChannel.close();
        socketChannel.close();

    }


    @Test
    public void server() throws IOException {

        ServerSocketChannel ssChannel = ServerSocketChannel.open();


        FileChannel outChannel = FileChannel.open(Paths.get("./k.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ssChannel.bind(new InetSocketAddress(9090));

        SocketChannel sChannel = ssChannel.accept();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();

        }

        buf.put("服务端接受数据成功".getBytes());
        buf.flip();

        sChannel.write(buf);

        sChannel.close();
        ssChannel.close();

        System.out.println("服务端关闭了====");

    }
}
