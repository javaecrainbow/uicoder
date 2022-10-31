package com.salk.uicoder.persist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用程序
 *
 * @author salkli
 * @date 2022/10/11
 */
@SpringBootApplication
@MapperScan("com.salk.uicoder.persist.api")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
