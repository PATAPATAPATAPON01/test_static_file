package com.example.demo;

import java.io.*;

/**
 * Created by PataPon on 2017/11/5.
 */
public class TestSerialize implements Serializable {

    public byte version = 100;

    public byte count = 0;


    public static void main(String[] args) throws Exception {
        File file = new File("./tt.out");
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream stream = new ObjectOutputStream(outputStream);
        stream.writeObject(new TestSerialize());
        stream.flush();
        stream.close();

        System.out.println(file.getAbsolutePath());


        /** 反序列化 */
        FileInputStream fileInputStream = new FileInputStream(file);

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        TestSerialize o = (TestSerialize) objectInputStream.readObject();
        System.out.println(o.version);
        System.out.println(o.count);
    }
}
