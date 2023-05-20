package com.bdg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class AmsMultiModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmsMultiModuleApplication.class, args);
    }
}