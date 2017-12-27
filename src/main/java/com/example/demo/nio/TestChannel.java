package com.example.demo.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

/**
 * Created by PataPon on 2017/12/23.
 */
public class TestChannel {


    @Test
    public void method() throws IOException {


        FileInputStream fileInputStream = new FileInputStream("/Users/PataPon/IdeaProjects/test_static_file/src/main/resources/a.txt");
        FileChannel inChannel = fileInputStream.getChannel();

        System.out.println(this.getClass().getPackage().getName().replace(".", "/"));
        FileOutputStream outputStream = new FileOutputStream("./b.txt");
        FileChannel outChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while ((inChannel.read(buffer)) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();


    }

    @Test
    public void method2() {
        String packName = this.getClass().getPackage().getName();
        System.out.println(packName);
        System.out.println(packName.replace(".", ";"));

        Pattern pattern = Pattern.compile("\\.");
        System.out.println(pattern.matcher(".").matches());

        Pattern pattern1 = Pattern.compile("\\.".toString());
        System.out.println(pattern1.matcher(".").matches());

    }

    @Test
    public void method3() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/PataPon/IdeaProjects/test_static_file/src/main/resources/a.txt"), StandardOpenOption.READ);

        FileChannel outChannel = FileChannel.open(Paths.get("c.txt"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);


        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());


        MappedByteBuffer outmappedByteBuffer1 = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());


        byte[] bytes = new byte[10];

        int remaining = mappedByteBuffer.remaining();

        while (mappedByteBuffer.hasRemaining()) {

            if (mappedByteBuffer.remaining() < 10) {
                mappedByteBuffer.get(bytes, 0, remaining);
                outmappedByteBuffer1.put(bytes, 0, remaining);
            } else {
                mappedByteBuffer.get(bytes);
                outmappedByteBuffer1.put(bytes);
            }
            remaining = mappedByteBuffer.remaining();
        }

        fileChannel.close();
        outChannel.close();
    }

    @Test
    public void method4() throws IOException {

        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/PataPon/IdeaProjects/test_static_file/src/main/resources/a.txt"), StandardOpenOption.READ);

        FileChannel outChannel = FileChannel.open(Paths.get("D.txt"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);


        fileChannel.transferTo(0, fileChannel.size(), outChannel);
        fileChannel.close();
        outChannel.close();
    }


    @Test
    public void method5() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/PataPon/IdeaProjects/test_static_file/src/main/resources/a.txt"), StandardOpenOption.READ);


        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(100);

        fileChannel.read(new ByteBuffer[]{buffer1, buffer2});
        buffer1.flip();
        buffer2.flip();

        System.out.println(new String(buffer1.array(), 0, buffer1.limit()));
        System.out.println(new String(buffer2.array(), 0, buffer2.limit()));

    }

    @Test
    public void method6() throws CharacterCodingException {
        Charset charset = Charset.forName("GBK");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("尚硅谷威武:");
        charBuffer.flip();

        //编码
        ByteBuffer byteBuffer = encoder.encode(charBuffer);

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        for (int i = 0; i < 10; i++) {

//            System.out.println(byteBuffer.get());

        }
        CharBuffer charBuffer1 = decoder.decode(byteBuffer);

        System.out.println(charBuffer1.toString());

        Charset charset1 = Charset.forName("UTF-8");
        byteBuffer.flip();


        CharBuffer charBuffer2 = charset1.decode(byteBuffer);
        System.out.println(charBuffer2.toString());

    }
}
