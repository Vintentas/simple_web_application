package org.example.springtest;

import org.apache.ibatis.type.MappedTypes;
import org.example.springtest.entities.User;
import org.example.springtest.entities.UserLogger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes({User.class, UserLogger.class})
@MapperScan("org.example.springtest.dao.mybatis")
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}