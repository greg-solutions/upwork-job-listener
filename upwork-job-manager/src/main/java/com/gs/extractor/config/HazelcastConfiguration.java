package com.gs.extractor.config;

import com.gs.common.hazelcast.repository.JobCacheRepository;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import net.javacrumbs.shedlock.provider.hazelcast.HazelcastLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

@Configuration
@EnableHazelcastRepositories(basePackageClasses = {JobCacheRepository.class})
public class HazelcastConfiguration {
    @Bean
    HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }

    @Bean
    public HazelcastLockProvider lockProvider(HazelcastInstance hazelcastInstance) {
        return new HazelcastLockProvider(hazelcastInstance);
    }
}
