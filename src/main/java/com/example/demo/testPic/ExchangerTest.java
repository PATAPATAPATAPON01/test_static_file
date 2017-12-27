package com.example.demo.testPic;

import java.io.IOException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by PataPon on 2017/12/18.
 */
public class ExchangerTest {

    public static void main(String[] args) throws IOException {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread t1 = new Thread(() -> {

            try {
                String threadA = exchanger.exchange("线程A",2, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + "  " + threadA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
        t1.setName("Thread--A");
        t1.start();
        int read = System.in.read();
        Thread t2 = new Thread(() -> {
            try {
                String threadB = exchanger.exchange("线程B");
                System.out.println(Thread.currentThread().getName() + "  " + threadB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });
        t2.setName("Thread---B");
        t2.start();
    }
}
