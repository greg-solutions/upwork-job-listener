package com.upwork.config;

import com.upwork.apis.UpworkApi;
import com.upwork.service.UpworkService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class AppConfiguration {

    public AppConfiguration() {
    }

    @Bean
    public static UpworkApi upworkApi() {

        return new UpworkApi();
    }


    @Bean
    public UpworkService upworkService(UpworkApi upworkApi) {
        return new UpworkService(upworkApi);
    }

}
