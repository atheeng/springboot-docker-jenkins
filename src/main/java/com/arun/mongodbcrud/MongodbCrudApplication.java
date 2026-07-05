package com.arun.mongodbcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MongodbCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbCrudApplication.class, args);
    }
}
