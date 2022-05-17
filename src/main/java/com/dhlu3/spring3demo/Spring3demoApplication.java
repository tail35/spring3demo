package com.dhlu3.spring3demo;


import com.config.MyConfig;
import com.config.MyXMLBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@EnableScheduling
@SpringBootApplication
@ComponentScan("com.controller")//load to main run,then you can use multi controler class.
@Component
@EnableConfigurationProperties({
        MyConfig.class
})

public class Spring3demoApplication {
    static public MyXMLBean obj;
    public static void main(String[] args) {
        try {
            //laod xml config as bean
            File resDir = new File("src/main/resources");
            String beanDir = resDir.getCanonicalPath()+"/Beans.xml";

            System.out.println("3main:" + beanDir);

            ApplicationContext context = new FileSystemXmlApplicationContext(beanDir);
            obj = (MyXMLBean) context.getBean("MyXMLBean");
            String resstr =  obj.getMessage();
            System.out.println("3main:" + resstr);

        }catch (IOException e){

        }
        SpringApplication.run(Spring3demoApplication.class, args);
    }

}
