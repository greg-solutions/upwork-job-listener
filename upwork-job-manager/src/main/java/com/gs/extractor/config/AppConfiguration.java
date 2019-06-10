package com.gs.extractor.config;

import com.gs.extractor.service.UpworkService;
import com.gs.extractor.upwork.UpworkApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.gs.common.service",
        "com.gs.provider",
        "com.gs.extractor"
})
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
