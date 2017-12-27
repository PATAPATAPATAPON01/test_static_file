package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by PataPon on 2017/11/7.
 */
public class TicketLock {

    AtomicInteger serviceNum = new AtomicInteger(); //服务号
    AtomicInteger ticketNum = new AtomicInteger(); //排队号
    int myTicket = 0;

    public void lock() {

        myTicket = this.ticketNum.getAndIncrement();

        while (serviceNum.get() != myTicket) {

        }

    }

    public void unlock() {

        serviceNum.compareAndSet(myTicket, myTicket + 1);
    }

    public static void main(String[] args) {
        AtomicInteger currentNum = new AtomicInteger();
        System.out.println(currentNum.getAndSet(1));
    }
}
