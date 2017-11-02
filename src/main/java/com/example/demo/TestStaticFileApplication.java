package com.example.demo;

import com.example.demo.event.MyApplicationEvent;
import com.example.demo.event.MyApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestStaticFileApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(TestStaticFileApplication.class, args);

//        context.addApplicationListener(new MyApplicationStartedEventListener());

        SpringApplication app = new SpringApplication(TestStaticFileApplication.class);
        app.addListeners(new MyApplicationListener());
        ConfigurableApplicationContext context = app.run(args);
        context.publishEvent(new MyApplicationEvent(new Object(), "我发布了事件"));


    }
}
