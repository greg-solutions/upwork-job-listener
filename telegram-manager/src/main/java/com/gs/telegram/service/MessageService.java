package com.gs.telegram.service;

import com.gs.telegram.message.request.StartMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageService {


    private final CharService charService;

    public MessageService(CharService charService) {

        this.charService = charService;
    }

    public void onMessage(StartMessage message) {
        charService.add(message.getChatMetaData());
    }

}
