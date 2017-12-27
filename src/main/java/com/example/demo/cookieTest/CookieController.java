package com.example.demo.cookieTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by PataPon on 2017/11/12.
 */
@RestController
public class CookieController {

    @GetMapping("/cookie")
    public void main(HttpServletRequest request, HttpSession session) {


        System.out.println(session.getAttribute("key"));
        session.setAttribute("key", "value11111");

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            System.out.println(cookie.getValue());
            System.out.println(cookie.getName());

        }

        Set<Map.Entry<Object, Object>> set = new HashMap<>().entrySet();
    }

}
