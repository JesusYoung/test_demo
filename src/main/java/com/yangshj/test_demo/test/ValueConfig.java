package com.yangshj.test_demo.test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "websocket.constant")
public class ValueConfig {

    private String ip;
    private int port;
}
