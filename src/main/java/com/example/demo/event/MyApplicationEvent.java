package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * 定义事件
 *
 */
public class MyApplicationEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private String msg;
    public MyApplicationEvent(Object source,String msg) {
        super(source);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }
}
