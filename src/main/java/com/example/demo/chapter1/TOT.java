package com.example.demo.chapter1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2017/9/29 15:55
 * DESC:
 */
public class TOT {

    public static void main(String[] args) throws Exception {



        URL url = new URL("http://localhost:8080");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        FileOutputStream outputStream = new FileOutputStream(new File("./111o.html"));
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.close();
        inputStream.close();
    }
}
