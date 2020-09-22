package com.alex.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class JavaApplication {

    public static void main(String[] args) {


        SpringApplication.run(JavaApplication.class, args);

    }

}

