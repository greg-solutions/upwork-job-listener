package com.gs.telegram.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import net.javacrumbs.shedlock.provider.hazelcast.HazelcastLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

@Configuration
@EnableHazelcastRepositories(basePackages = {"com.gs.common.hazelcast.repository"})
public class HazelcastConfiguration {

    @Bean
    public HazelcastLockProvider lockProvider(HazelcastInstance hazelcastInstance) {
        return new HazelcastLockProvider(hazelcastInstance);
    }

}
