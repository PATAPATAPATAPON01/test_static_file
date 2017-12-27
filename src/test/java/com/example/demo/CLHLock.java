package com.example.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by PataPon on 2017/11/7.
 */
public class CLHLock implements Lock {

    AtomicReference<Qnode> tail = new AtomicReference<>();
    ThreadLocal<Qnode> pred = new ThreadLocal<>();
    ThreadLocal<Qnode> myNode = new ThreadLocal<>();

    public CLHLock() {


        myNode = new ThreadLocal<Qnode>() {

            protected Qnode initialValue() {
                return new Qnode();
            }

        };

    }

    @Override
    public void lock() {

        Qnode qnode = myNode.get();
        qnode.locked = true;
        Qnode preNode = tail.getAndSet(qnode);
        while (preNode.locked) {

        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

        Qnode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(null);


    }

    @Override
    public Condition newCondition() {
        return null;
    }

    class Qnode {

        boolean locked = false;

    }
}
