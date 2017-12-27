package com.example.demo.concurrentUtils;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by PataPon on 2017/12/17.
 */
public class MT {

    @Test
    public void method() throws InterruptedException, BrokenBarrierException {

//        CountDownLatch latch = new CountDownLatch(2);
//        latch.countDown();
//        latch.await();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println(Thread.currentThread().getName());
        });
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        cyclicBarrier.await();
        System.out.println("2222");
        System.out.println(cyclicBarrier.getNumberWaiting());
        cyclicBarrier.reset();

        cyclicBarrier.await();
    }
}
