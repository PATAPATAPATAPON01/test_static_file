package com.example.demo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by PataPon on 2017/11/7.
 */
public class SpinLock {

    AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {

        while (owner.compareAndSet(null, Thread.currentThread())) {

        }
    }


    public void unlock() {

        owner.compareAndSet(Thread.currentThread(), null);

    }
}
