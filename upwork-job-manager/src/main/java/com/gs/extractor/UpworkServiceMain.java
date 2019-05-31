package com.gs.extractor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableMongoRepositories(basePackages = {
        "com.gs.common.repository",
        "com.gs.extractor.repository",
})
@EnableAspectJAutoProxy
public class UpworkServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(UpworkServiceMain.class, args);
    }

}