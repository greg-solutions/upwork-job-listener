package com.gs.telegram.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
@Document(collection = "chats")
public class ChatModel {
    private Long chatId;
    private User user;


}
