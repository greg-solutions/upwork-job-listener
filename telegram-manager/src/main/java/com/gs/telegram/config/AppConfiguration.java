package com.gs.telegram.config;

import com.gs.telegram.ChannelHandler;
import com.gs.telegram.properties.BotProperties;
import com.gs.telegram.service.MessageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Configuration
@ComponentScan(basePackages = {
        "com.gs.common",
        "com.gs.provider",
        "com.gs.telegram"

})
@EnableConfigurationProperties
public class AppConfiguration {
    @Bean
    public TelegramBotsApi telegramBotsApi(ChannelHandler channelHandler) throws TelegramApiRequestException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(channelHandler);
        return telegramBotsApi;
    }

    @Bean
    public ChannelHandler channelHandler(MessageService messageService, BotProperties botProperties) {
        return new ChannelHandler(messageService, botProperties);
    }
}
