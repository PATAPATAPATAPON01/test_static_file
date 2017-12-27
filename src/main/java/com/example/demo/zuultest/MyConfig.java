package com.example.demo.zuultest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by PataPon on 2017/12/6.
 */

//@Configuration
public class MyConfig {


    @Bean
    public Student getStu() {
        System.out.println("student=====");
        return new Student();
    }

    @Configuration
    static class innerClass {


        @Bean()
        public User getUser() {
            System.err.println("=========================");
            return new User();
        }
    }
}
