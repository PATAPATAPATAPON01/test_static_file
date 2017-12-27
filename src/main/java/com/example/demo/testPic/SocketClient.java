package com.example.demo.testPic;



import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by PataPon on 2017/12/23.
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9090);
        socket.getInputStream().read();
//        OutputStream stream = socket.getOutputStream();
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
//
//        writer.write("wo shi lvjian \n");
//        writer.flush();
//        int len=0;
//        byte data[]=new byte[4096];
//        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
//        int read = socket.getChannel().read(byteBuffer);
//        while (read!=-1){
//            byteBuffer.flip();
//            byteBuffer.get(data);
//            System.out.println(new String(data,0,byteBuffer.limit()));
//        }

    }
}
