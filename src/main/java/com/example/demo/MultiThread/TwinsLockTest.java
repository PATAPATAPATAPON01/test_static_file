package com.example.demo.MultiThread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by PataPon on 2017/12/3.
 */
public class TwinsLockTest {
    static TwinsLock lock = new TwinsLock();

    static class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();

                try {

                    Thread.sleep(1l);
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1l);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
//            worker.setDaemon(true);
            worker.start();
        }

        for (int i = 0; i < 10; i++) {

            Thread.sleep(1000l);
            System.out.println();

        }
    }
}
