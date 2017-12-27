package com.example.demo.testPic;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by PataPon on 2017/12/21.
 */
public class sssqqq {

    public static void main(String[] args) throws IOException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        queue.put("1");
        System.out.println(queue.size());


        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {

                while (true){

                    HashMap<String, Object> stringObjectHashMap = new HashMap<>(2048);
                    System.out.println(Thread.currentThread().getName()+""+1);
                }

            }).start();
        }
        System.in.read();
    }
}
