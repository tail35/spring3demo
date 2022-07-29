package com.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
//reference url: https://www.cnblogs.com/chenpi/p/9696310.html
//need @ComponentScan("com.task") and @EnableScheduling at application first statement.
@Component
@Order(value = 2) //if multip CommandLineRunner object value is execute order.
public class MyCommandLineRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);
    @Override//only execute once after start app.
    public void run(String... var1) throws Exception{
        System.out.println("This will be execute when the project was started!");
        int i=0;
        i++;
        logger.debug("11111");
    }
}
