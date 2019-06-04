package com.gs.lighthouse;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.gs.akka.config", "com.gs.akka.listener"})
public class AppConfiguration {
    @Bean
    public Config akkaConfig() {

        return ConfigFactory.load();
    }
}
