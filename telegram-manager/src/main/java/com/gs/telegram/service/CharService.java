package com.gs.telegram.service;

import com.gs.common.GenericBuilder;
import com.gs.telegram.message.request.ChatMetaData;
import com.gs.telegram.model.ChatModel;
import com.gs.telegram.repository.ChatRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CharService {

    private final ChatRepository chatRepository;

    public CharService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void add(ChatMetaData chat) {
        ChatModel chatModel = GenericBuilder
                .of(ChatModel::new)
                .with(ChatModel::setChatId, chat.getChatId())
                .with(ChatModel::setUser, chat.getUser())
                .build();
        chatRepository.save(chatModel);
    }

    public List<ChatModel> getAll() {
        return chatRepository.findAll();
    }
}
