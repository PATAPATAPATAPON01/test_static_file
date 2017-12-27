package com.example.demo.MultiThread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by PataPon on 2017/12/3.
 */
public class ReadWriteLockTest {


    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        readWriteLock.getReadLockCount();
        readWriteLock.getReadHoldCount();
        readWriteLock.isWriteLockedByCurrentThread();
        readWriteLock.getWriteHoldCount();
    }
}
