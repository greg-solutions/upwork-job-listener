package com.gs.lighthouse;

import com.gs.akka.config.AkkaConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "akka")
@Configuration
public class LighthouseAkkaConfig extends AkkaConfig {
}
