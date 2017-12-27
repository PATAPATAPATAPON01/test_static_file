package com.example.demo.atomic_learn;

/**
 * Created by PataPon on 2017/12/17.
 */
public class User {

    volatile String name;
    private int old;

    public String getName() {
        return name;
    }

    volatile int age;

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }
}
