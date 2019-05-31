package com.gs.api.config;

import com.gs.common.repository.QueryRepository;
import com.gs.common.service.QueryService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.gs.common",
        "com.gs.api"
})
@EnableConfigurationProperties
public class ServiceConfiguration {

    @Bean
    public QueryService queryService(QueryRepository queryRepository) {

        return new QueryService(queryRepository);
    }


}
