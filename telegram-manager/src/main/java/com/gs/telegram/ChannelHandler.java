package com.gs.telegram;

import com.gs.telegram.message.request.*;
import com.gs.telegram.properties.BotProperties;
import com.gs.telegram.service.MessageService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.logging.BotLogger;

import java.util.Objects;
import java.util.logging.Logger;

public class ChannelHandler extends TelegramLongPollingBot {

    private static final Logger logger = Logger.getLogger(ChannelHandler.class.getName());
    private static final String LOGTAG = "CHANNELHANDLERS";
    private static final String CHANNEL_MESSAGE_TEXT = "This message was sent by *@updateschannelbot*. Enjoy!";
    private final BotProperties botProperties;
    private final MessageService messageService;

    public ChannelHandler(MessageService messageService, BotProperties botProperties) {
        this.botProperties = botProperties;
        this.messageService = messageService;
    }


    private void onMessage(Message message, String callbackCommand, String callbackId) {

        logger.info(message.getText());
        if (message.hasEntities()) {
            if (message.getEntities().get(0) != null && Objects.equals(message.getEntities().get(0).getType(), "bot_command")) {
                MessageEntity messageEntity = message.getEntities().get(0);
                ChatMessage chatMessage;
                String command = messageEntity.getText();
                String body = message.getText().substring(messageEntity.getLength());
                switch (command) {
                    case Commands.startCommand:

                        messageService.onMessage(new StartMessage(create(message)));
                        break;

                    default:
                        chatMessage = new TextMessage(create(message), message.getText());
                }

                return;
            }
        }
        if (callbackCommand != null) {
            ChatMessage chatMessage;
            chatMessage = new CallbackChatMessage(create(message), callbackCommand, callbackId);

        }

    }


    private ChatMetaData create(Message message) {
        return new ChatMetaData(message.getChatId(), message.getMessageId(), message.getFrom());
    }

    private void onMessage(Message message) {

        onMessage(message, null, null);
    }


    private void onCallbackMessage(Message message, String callbackCommand, String callbackId) {
        onMessage(message, callbackCommand, callbackId);

    }


    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                onMessage(message);

                return;
            }
            if (update.hasCallbackQuery()) {
                onCallbackMessage(update.getCallbackQuery().getMessage(), update.getCallbackQuery().getData(), update.getCallbackQuery().getId());
            }
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }
    }


    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }
}
