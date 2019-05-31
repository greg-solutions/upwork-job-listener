package com.gs.telegram;

import com.gs.common.hazelcast.model.JobCachedModel;
import com.gs.common.hazelcast.repository.JobCacheRepository;
import com.gs.telegram.message.response.JobMessageBuilder;
import com.gs.telegram.service.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Optional;

@Slf4j
public class MessageListener {

    private final JobCacheRepository jobCacheRepository;
    private final SenderService senderService;

    public MessageListener(JobCacheRepository jobCacheRepository, SenderService senderService) {

        this.jobCacheRepository = jobCacheRepository;
        this.senderService = senderService;
    }

    @Scheduled(cron = "${scheduler.job}")
    public void checkNewJob() {


        try {
            Optional<JobCachedModel> model = jobCacheRepository.findFirstByOrderByIdAsc();
            if (!model.isPresent()) {
                log.debug("No messages were found");
                return;
            }
            senderService.send(new JobMessageBuilder(model.get()).build());
            jobCacheRepository.delete(model.get());
        } catch (Exception e) {
            log.error("Send message to chat is failed.", e);
        }

    }
}
