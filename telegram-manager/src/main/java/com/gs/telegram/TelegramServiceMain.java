package com.gs.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@Slf4j
@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableMongoRepositories(basePackages = {
        "com.gs.mongo.repository",
        "com.gs.telegram.repository",
})
@EnableAspectJAutoProxy
public class TelegramServiceMain {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramServiceMain.class, args);
    }

}