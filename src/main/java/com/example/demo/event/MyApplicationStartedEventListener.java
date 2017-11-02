package com.example.demo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2017/11/2 18:20
 * DESC:
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartingEvent> {
    private Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        SpringApplication app = event.getSpringApplication();
        logger.info("==MyApplicationStartedEventListener==");
    }
}
