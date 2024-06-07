package com.example.demo3.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo3.mapper")
public class MpdemoApplication {
    public static void main(String[] args){
        SpringApplication.run(MpdemoApplication.class);
    }
}
