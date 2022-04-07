package com.dhlu3.spring3demo;


import com.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@EnableScheduling
@SpringBootApplication
@ComponentScan("com.controller")//load to main run,then you can use multi controler class.
@Component
@EnableConfigurationProperties({
        MyConfig.class
})

public class Spring3demoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring3demoApplication.class, args);
    }

}
