package com.gs.telegram;

import com.gs.common.JobTransportProvider;
import com.gs.common.Model;
import com.gs.common.model.JobModel;
import com.gs.telegram.service.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Optional;

@Slf4j
public class MessageListener {

    private final JobTransportProvider transportProvider;
    private final SenderService senderService;

    public MessageListener(JobTransportProvider transportProvider, SenderService senderService) {

        this.transportProvider = transportProvider;
        this.senderService = senderService;
    }

    @Scheduled(cron = "${scheduler.job}")
    public void checkNewJob() {


        try {
            Optional<Model<JobModel>> model = transportProvider.get();
            if (!model.isPresent()) {
                log.debug("No messages were found");
                return;
            }
            senderService.send(model.get().getModel());
            transportProvider.delete(model.get());
        } catch (Exception e) {
            log.error("Send message to chat is failed.", e);
        }

    }
}
