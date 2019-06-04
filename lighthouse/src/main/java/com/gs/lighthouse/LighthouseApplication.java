package com.gs.lighthouse;

import akka.actor.ActorSystem;
import com.gs.akka.config.SpringProps;
import com.gs.akka.listener.ClusterListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
@SpringBootApplication
public class LighthouseApplication implements CommandLineRunner, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Autowired
    ActorSystem actorSystem;

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(LighthouseApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LighthouseApplication.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) {
        actorSystem.actorOf(SpringProps.create(actorSystem, ClusterListener.class));
        log.info("Setup Lighthouse");
    }
}