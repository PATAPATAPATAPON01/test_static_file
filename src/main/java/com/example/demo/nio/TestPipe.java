package com.example.demo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by PataPon on 2017/12/24.
 */
public class TestPipe {


    public static void main(String[] args) throws IOException {


        Pipe pipe = Pipe.open();

        //把缓冲区的数据写入缓冲区
        Pipe.SinkChannel sinkChannel = pipe.sink();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("通过单向通道发送数据".getBytes());

        buffer.flip();

        sinkChannel.write(buffer);


        //读取缓冲区的数据
        Pipe.SourceChannel sourceChannel = pipe.source();

        buffer.clear();
        int len = sourceChannel.read(buffer);

        System.out.println(new String(buffer.array(), 0, len));

        sourceChannel.close();
        sinkChannel.close();
    }

}
