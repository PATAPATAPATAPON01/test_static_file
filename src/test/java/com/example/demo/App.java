package com.example.demo;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by PataPon on 2017/11/7.
 */
public class App {


    public static void main(String[] args) {
        AtomicReferenceFieldUpdater<Dog, String> updater = AtomicReferenceFieldUpdater.newUpdater(Dog.class, String.class, "name");


        Dog dog = new Dog();
        updater.compareAndSet(dog, "dog1", "test");

        System.out.println(dog.name);

    }


    static class Dog {

        volatile String name = "dog1";
    }
}
