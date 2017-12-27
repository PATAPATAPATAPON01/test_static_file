package com.example.demo.cookieTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by PataPon on 2017/11/12.
 */
@RestController
public class NginxController {


    @GetMapping("/ok")
    public String method(HttpServletRequest request) {

        System.out.println("====");

        return "服务到达---->端口"+request.getLocalPort();
    }

}