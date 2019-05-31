package com.gs.telegram.service;

import com.gs.telegram.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SenderService {

    private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
    private final ChannelHandler sender;
    private final CharService charService;

    public SenderService(ChannelHandler sender, CharService charService) {
        this.sender = sender;
        this.charService = charService;
    }

    public String removeTags(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        Matcher m = REMOVE_TAGS.matcher(string);
        return m.replaceAll("");
    }

    public void send(String message) {
        charService.getAll().forEach(chatMetaData -> {
            SendMessage sendDocument = new SendMessage();
            sendDocument.setChatId(chatMetaData.getChatId());
            sendDocument.setText(removeTags(message));
            sendDocument.enableMarkdown(true);

            try {
                sender.executeAsync(sendDocument, new SentCallback<Message>() {
                    @Override
                    public void onResult(BotApiMethod<Message> method, Message response) {
                        log.debug("Message send is success. Message: {}", method.getMethod());
                    }

                    @Override
                    public void onError(BotApiMethod<Message> method, TelegramApiRequestException apiException) {
                        log.debug("Message send is failed. Message: {}", method.getMethod());
                    }

                    @Override
                    public void onException(BotApiMethod<Message> method, Exception exception) {
                        log.debug("Message send exception. Message: {}", method.getMethod());
                    }
                });
            } catch (TelegramApiException e) {
                log.error("Send message exception", e);
            }
        });
    }
}
