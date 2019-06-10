package com.gs.provider.hazelcast.config;

import com.gs.common.JobTransportProvider;
import com.gs.provider.hazelcast.repository.JobHazelcastRepository;
import com.gs.provider.hazelcast.service.HazelcastJobTransportProvider;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

@Configuration
@EnableHazelcastRepositories(basePackageClasses = {JobHazelcastRepository.class})
public class HazelcastConfiguration {
    @Bean
    HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }

    @Bean
    public JobTransportProvider transportProvider(JobHazelcastRepository repository) {
        return new HazelcastJobTransportProvider(repository);
    }
}
