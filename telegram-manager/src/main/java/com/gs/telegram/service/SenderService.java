package com.gs.telegram.service;

import com.gs.common.hazelcast.model.JobCachedModel;
import com.gs.common.hazelcast.repository.JobCacheRepository;
import com.gs.telegram.ChannelHandler;
import com.gs.telegram.message.response.JobMessageBuilder;
import com.gs.telegram.model.ChatModel;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SenderService {

    private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
    private final ChannelHandler sender;
    private final CharService charService;
    private final JobCacheRepository jobCacheRepository;

    public SenderService(ChannelHandler sender, CharService charService, JobCacheRepository jobCacheRepository) {
        this.sender = sender;
        this.charService = charService;
        this.jobCacheRepository = jobCacheRepository;
    }

    public String removeTags(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        Matcher m = REMOVE_TAGS.matcher(string);
        return m.replaceAll("");
    }

    public void send(String message, ChatModel chat) {

        SendMessage sendDocument = new SendMessage();
        sendDocument.setChatId(chat.getChatId());
        sendDocument.setText(removeTags(message));
        sendDocument.enableMarkdown(true);

        try {
            sender.execute(sendDocument);
        } catch (TelegramApiException e) {
            log.error("Send message exception", e);
        }

    }

    public void send(JobCachedModel model) {
        String message = new JobMessageBuilder(model).build();
        List<ChatModel> chats = charService.getAll();
        if (chats.isEmpty()) {
            return;
        }
        charService.getAll().forEach(chat -> send(message, chat));
        jobCacheRepository.delete(model);
    }
}
