package com.controller;//package error easy case can't quest web

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/my")  //if has my ,ip:port/my/hello, or ip:port/hello
public class helloword {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
}
