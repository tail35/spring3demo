package com.controller;//package error easy case can't quest web


import com.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/my")  //if has my ,ip:port/my/hello, or ip:port/hello
public class helloword {
    @Autowired
    private MyConfig mMyconfig;
    @GetMapping("/hello")
    public String sayHello() {

        String tmp = mMyconfig.uris+mMyconfig.username+mMyconfig.password+"Hello World";
        return tmp;
    }
}