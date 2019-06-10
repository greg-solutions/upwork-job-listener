package com.gs.telegram.config;

import com.gs.common.JobTransportProvider;
import com.gs.telegram.MessageListener;
import com.gs.telegram.service.SenderService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ListenerConfiguration {
    @Bean
    public MessageListener messageListener(JobTransportProvider transportProvider, SenderService senderService) {

        return new MessageListener(transportProvider, senderService);
    }

}
