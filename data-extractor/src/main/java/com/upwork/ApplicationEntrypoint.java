package com.upwork;

import com.upwork.apis.UpworkApi;
import com.upwork.models.JobModel;
import com.upwork.service.UpworkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@Slf4j
public class ApplicationEntrypoint implements CommandLineRunner, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationEntrypoint.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationEntrypoint.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) {
        log.info("Start Upwork Extractor");
        String searchQuery = "docker";
        UpworkApi upworkApi = new UpworkApi();
        UpworkService upworkService = new UpworkService(upworkApi);

        List<JobModel> jobModelList = upworkService.searchJob(searchQuery);
        log.info("new items");

    }
}
