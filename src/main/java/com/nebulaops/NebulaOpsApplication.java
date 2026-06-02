package com.nebulaops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NebulaOpsApplication {
    public static void main(String[] args) {
        SpringApplication.run(NebulaOpsApplication.class, args);
    }
}
