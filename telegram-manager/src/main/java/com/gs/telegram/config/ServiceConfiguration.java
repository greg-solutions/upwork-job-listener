package com.gs.telegram.config;

import com.gs.common.JobTransportProvider;
import com.gs.telegram.ChannelHandler;
import com.gs.telegram.repository.ChatRepository;
import com.gs.telegram.service.CharService;
import com.gs.telegram.service.MessageService;
import com.gs.telegram.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ServiceConfiguration {

    @Autowired
    private ChatRepository chatRepository;

    @Bean
    public MessageService messageService() {
        return new MessageService(charService());
    }

    @Bean
    public CharService charService() {
        return new CharService(chatRepository);
    }

    @Bean
    public SenderService senderService(ChannelHandler sender, JobTransportProvider transportProvider) {
        return new SenderService(sender, charService(), transportProvider);
    }
}
