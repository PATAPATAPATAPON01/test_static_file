package com.example.demo.testPic;

import com.google.common.io.ByteStreams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Created by PataPon on 2017/11/22.
 */
@RestController
public class PicController {

    @GetMapping("/pic")
    public void getPic(HttpServletResponse res) throws Exception {


        InputStream inputStream = this.getClass().getResourceAsStream("/static/Desert.jpg");

        ServletOutputStream outputStream = res.getOutputStream();
//
//        byte[] bytes = new byte[1024];
//        int len = 0;
//        while ((len = inputStream.read(bytes)) != -1) {
//            outputStream.write(bytes, 0, len);
//        }
//
//        outputStream.close();


        res.setHeader("Accept-Ranges","bytes");
        res.setHeader("lvjian", "buzd");
        res.setHeader("Content-Type","image/gif");

        ByteStreams.copy(inputStream, outputStream);

        System.out.println(res.getHeader("Content-Length"));
        outputStream.close();
    }
}
