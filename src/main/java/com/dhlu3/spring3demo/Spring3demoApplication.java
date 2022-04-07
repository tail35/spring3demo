package com.dhlu3.spring3demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@EnableScheduling
@SpringBootApplication
@ComponentScan("com.controller")//load to main run,then you can use multi controler class.
@Component

public class Spring3demoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring3demoApplication.class, args);
    }

}
