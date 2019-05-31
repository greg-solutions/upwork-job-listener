package com.gs.extractor.config;

import com.gs.common.hazelcast.repository.JobCacheRepository;
import com.gs.common.service.QueryService;
import com.gs.extractor.JobListener;
import com.gs.extractor.service.JobService;
import com.gs.extractor.service.UpworkService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ListenerConfiguration {

    public ListenerConfiguration() {
    }

    @Bean
    public JobListener jobListener(JobService jobService, QueryService queryService,
                                   UpworkService upworkService, JobCacheRepository jobCacheRepository) {

        return new JobListener(queryService, jobService, upworkService, jobCacheRepository);
    }


}
