package com.example.demo;

import com.example.demo.event.MyApplicationEvent;
import com.example.demo.event.MyApplicationListener;
import com.example.demo.zuultest.Student;
import com.example.demo.zuultest.SubConfig;
import com.example.demo.zuultest.User;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.demo.zuultest.RateLimitProperties;


import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class TestStaticFileApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(TestStaticFileApplication.class, args);

//        context.addApplicationListener(new MyApplicationStartedEventListener());

        System.setProperty("servstargate-apigateway.ymler.port", "0");
        SpringApplication app = new SpringApplication(TestStaticFileApplication.class);
        app.addListeners(new MyApplicationListener());
        ConfigurableApplicationContext context = app.run(args);
        context.publishEvent(new MyApplicationEvent(new Object(), "我发布了事件"));
//        User bean = context.getBean(User.class);
//        System.out.println(bean == null);
//        Student student = context.getBean(Student.class);
//        System.out.println(student == null);
//        Class<Student> aClass = Student.class;
        RateLimitProperties rateLimitProperties = context.getBean(RateLimitProperties.class);
        System.out.println(rateLimitProperties);
        Logger logger=null;
    }
}
