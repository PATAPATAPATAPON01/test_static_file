package com.example.demo.testPic;

import java.util.concurrent.*;

/**
 * Created by PataPon on 2017/12/18.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        PriorityBlockingQueue<Object> objects = new PriorityBlockingQueue<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        executor.submit(() -> {
        });
        executor.submit(() -> {
        });
        executor.submit(() -> {
        });
        executor.submit(() -> {
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();

        System.out.println(executor.getActiveCount());
        System.out.println(executor.getCompletedTaskCount());
        System.out.println(executor.getTaskCount());

    }
}
