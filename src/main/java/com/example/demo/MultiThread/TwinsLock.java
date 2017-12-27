package com.example.demo.MultiThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by PataPon on 2017/12/3.
 */
public class TwinsLock implements Lock {

    private final sync sync = new sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {

        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }


    private static class sync extends AbstractQueuedSynchronizer {

        sync(int count) {

            if (count <= 0)
                throw new IllegalArgumentException("count must lager than zero.");
            setState(2);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {

            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;

                if (compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }

        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {

            for (; ; ) {

                int current = getState();
                int newCount = current + returnCount;

                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }

        }
    }
}
