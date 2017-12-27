package com.example.demo.testPic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by PataPon on 2017/12/18.
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(10);

        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < 30; i++) {
            pool.execute(() -> {

                try {
                    semaphore.acquire();
                    System.out.println("save data");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();
    }
}
