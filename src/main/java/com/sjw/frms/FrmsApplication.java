package com.sjw.frms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ="com.sjw.frms.dao")
public class FrmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrmsApplication.class, args);
    }

}
