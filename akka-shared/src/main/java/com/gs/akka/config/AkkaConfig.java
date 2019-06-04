package com.gs.akka.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AkkaConfig {
    private String host;
    private String port;
    private String systemName = "akka-system";
    private String lighthouseUrl;
}
