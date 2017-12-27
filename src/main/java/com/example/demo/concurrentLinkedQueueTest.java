package com.example.demo;

import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by PataPon on 2017/12/16.
 */
public class concurrentLinkedQueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Object> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

//        concurrentLinkedQueue.peek()

//        for (int i = 0, b = i; ; ) {
//            System.out.println(b);
//        }
        new ArrayBlockingQueue<String>(10).poll();

        new ReentrantLock().lock();
//        Executors.newCachedThreadPool().submit()

    }

    @Test
    public void method() throws InterruptedException {
//        PriorityBlockingQueue<Object> blockingQueue = new PriorityBlockingQueue<>(new Comparab());
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        synchronousQueue.put("1");
//            synchronousQueue.offer()


        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();

    }

    @Test
    public void method2() throws InterruptedException {

        LinkedTransferQueue<Integer> transferQueue = new LinkedTransferQueue<>();

        transferQueue.transfer(0);

    }


    @Test
    public void method3(){
        LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>();

    }
}
