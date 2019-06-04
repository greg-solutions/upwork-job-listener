package com.gs.akka.config;

import akka.actor.ActorSystem;
import akka.cluster.Cluster;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableConfigurationProperties
public class AkkaConfiguration {

    @Bean
    public Config akkaConfig() {
        return ConfigFactory.empty();
    }


    @Bean
    public Config journalConfig(Config akkaConfig) {
        return akkaConfig;
    }

    @Bean
    public Config networkConfig(Config journalConfig, AkkaConfig config) {
        String seed = "akka.tcp://" + config.getSystemName() + "@" + config.getLighthouseUrl();
        List<String> seeds = new ArrayList<>();
        seeds.add(seed);
        return journalConfig
                .withValue("akka.cluster.seed-nodes", ConfigValueFactory.fromIterable(seeds))
                .withValue("akka.remote.netty.tcp.hostname", ConfigValueFactory.fromAnyRef(config.getHost()))
                .withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(config.getPort()));
    }

    @Bean
    public ActorSystem actorSystem(ApplicationContext applicationContext, Config networkConfig, AkkaConfig config) {

        ActorSystem system = ActorSystem.create(config.getSystemName(), networkConfig.withFallback(ConfigFactory.load()));

        SpringExtension.getInstance().get(system)
                .initialize(applicationContext);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    Cluster cluster = Cluster.get(system);
                    cluster.leave(cluster.selfAddress());
                })
        );
        return system;
    }

    @Bean
    public Cluster actorCluster(ActorSystem system) {
        Cluster cluster = Cluster.get(system);
        return cluster;
    }
}
