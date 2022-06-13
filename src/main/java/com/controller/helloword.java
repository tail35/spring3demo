package com.controller;//package error easy case can't quest web


import com.config.MyConfig;
import com.dhlu3.spring3demo.Spring3demoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

//@RequestMapping("/my")  //if has my ,ip:port/my/hello, or ip:port/hello
public class helloword {
    @Autowired
    private MyConfig mMyconfig;
    @GetMapping("/hello")
    public String sayHello() {

        //String tmp = mMyconfig.uris+mMyconfig.username+mMyconfig.password+"Hello World";
        String tmp = Spring3demoApplication.obj.getMessage();//test xml bean.
        try {
            //Thread.sleep(10000*1000);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return tmp;
    }
}
