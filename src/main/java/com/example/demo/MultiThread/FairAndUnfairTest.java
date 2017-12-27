package com.example.demo.MultiThread;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by PataPon on 2017/12/3.
 */
public class FairAndUnfairTest {


    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unFairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        testLock(fairLock);
    }


    @Test
    public void unFair() {

        testLock(unFairLock);

    }

    private void testLock(ReentrantLock2 lock2) {

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {

                lock2.lock();

                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("locked By" + Thread.currentThread().getName() + ",waiting by   " + lock2.getQueuedThreads());
                lock2.unlock();
            });

            thread.setName("" + i);
            thread.start();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {

        ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            ArrayList<Thread> list = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(list);
            return list;
        }
    }
}
