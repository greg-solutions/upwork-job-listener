package com.gs.extractor.config;

import com.gs.common.repository.QueryRepository;
import com.gs.common.service.QueryService;
import com.gs.extractor.repository.JobRepository;
import com.gs.extractor.service.JobService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ServiceConfiguration {

    public ServiceConfiguration() {
    }

    @Bean
    public JobService jobService(JobRepository jobRepository) {

        return new JobService(jobRepository);
    }

    @Bean
    public QueryService queryService(QueryRepository queryRepository) {

        return new QueryService(queryRepository);
    }


}
