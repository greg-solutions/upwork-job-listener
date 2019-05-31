package com.gs.telegram.message.request;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
public class ChatMetaData {

    private Long chatId;
    private User user;
    private Integer messageId;

    public ChatMetaData(Long chatId, Integer messageId, User user) {
        this.chatId = chatId;
        this.user = user;
        this.messageId = messageId;
    }
}
