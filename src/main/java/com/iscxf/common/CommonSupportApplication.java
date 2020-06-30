package com.iscxf.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CommonSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonSupportApplication.class, args);
        System.out.println("-----application start success!-----");
    }

}
