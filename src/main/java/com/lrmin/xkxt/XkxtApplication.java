package com.lrmin.xkxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lrmin")
public class XkxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(XkxtApplication.class, args);
    }

}
