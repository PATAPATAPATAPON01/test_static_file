package com.example.demo.testPic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by PataPon on 2017/12/23.
 */
public class SocketServerTest {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);

        while (true) {

            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println(reader.readLine());
            socket.getOutputStream().write("我收到了".getBytes("utf-8"));
        }

    }
}
