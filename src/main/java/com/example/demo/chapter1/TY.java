package com.example.demo.chapter1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2017/9/29 17:44
 * DESC:
 */
public class TY {

    public static void main(String[] args) {

        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
//            System.out.println("Found value: " + m.group(2));
//            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }

        Pattern pattern2 = Pattern.compile("(.*)");
        Matcher matcher = r.matcher("This order was placed for QT3000");
        matcher.find();
        System.out.println(matcher.group(0));
    }
}
