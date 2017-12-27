package com.example.demo.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by PataPon on 2017/12/24.
 */
public class TestUDP {


    public static void main(String[] args) throws IOException {
        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        Scanner scanner = new Scanner(System.in);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (scanner.hasNext()) {

            String next = scanner.next();
            buffer.put((new Date().toString() + "\n" + next).getBytes());

            buffer.flip();

            dc.send(buffer, new InetSocketAddress("localhost", 9898));
        }

        dc.close();
    }


    @Test
    public void udpServer() throws IOException {

        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        dc.bind(new InetSocketAddress(9898));

        Selector selector = Selector.open();

        dc.register(selector, SelectionKey.OP_READ);


        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            SelectionKey sk = iterator.next();

            if (sk.isReadable()) {


                ByteBuffer buffer = ByteBuffer.allocate(1024);


                dc.receive(buffer);

                buffer.flip();

                System.out.println(new String(buffer.array(), 0, buffer.limit()));

            }

        }

    }
}
