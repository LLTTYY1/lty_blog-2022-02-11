package com.LTY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 刘泰源
 * @version 1.8
 */
@SpringBootApplication
@EnableSwagger2
public class LtyApplication {
    public static void main(String[] args) {
        SpringApplication.run(LtyApplication.class, args);
    }
}
