package com.example.demo.atomic_learn;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.atomic.*;

/**
 * Created by PataPon on 2017/12/17.
 */
public class AtmoicTest {

    @Test
    public void method() throws IOException {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.lazySet(false);

        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println();
        new Thread(() -> {
            System.out.println("执行l ");
            atomicInteger.lazySet(200);
        }).start();
        System.out.println(atomicInteger.get());

        System.in.read();

        atomicInteger.getAndIncrement();


    }


    @Test
    public void method2() {

        AtomicIntegerArray integer = new AtomicIntegerArray(100);
        System.out.println(integer.addAndGet(10, 1));
        System.out.println(integer.compareAndSet(10, 1, 100));
    }


    @Test
    public void method12() {

        int[] values = {1, 2};
        AtomicIntegerArray ai = new AtomicIntegerArray(values);
        System.out.println(ai.getAndSet(0, 3));
        System.out.println(ai.get(0));
        System.out.println(values[0]);

    }

    @Test
    public void method3() {
        AtomicReference<String> reference = new AtomicReference<>();
        reference.set("first");
        System.out.println(reference.compareAndSet("first", "second"));
    }

    @Test
    public void method4() {
        AtomicReferenceFieldUpdater<User, String> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");

        User user = new User();
        System.out.println(fieldUpdater.get(user));
        user.setName("lvjian");
        fieldUpdater.set(user, "lvjian");
        System.out.println(fieldUpdater.get(user));

    }

    @Test
    public void method42() {

        AtomicIntegerFieldUpdater<User> integerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        User user = new User();
        integerFieldUpdater.set(user, 100);
        System.out.println(integerFieldUpdater.get(user));
        String string = "ll";
        AtomicStampedReference<String> reference = new AtomicStampedReference<>(string, 1);
        reference.set("OO", 2);
        System.out.println(reference.get(new int[]{1, 2}));
        System.out.println(reference.getReference());
        System.out.println(reference.getStamp());
    }


    @Test
    public void method5(){
        String s1=new String("dodo");
        AtomicMarkableReference<String> markableReference = new AtomicMarkableReference<>(s1, false);

        String s2=new String("papatapon");
        System.out.println(markableReference.isMarked());

        markableReference.set(s2,true);
        System.out.println(markableReference.isMarked());
        System.out.println(markableReference.getReference());
        System.out.println(markableReference.get(new boolean[100]));
    }
}
