package com.material.light.broadcastservice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("mail.smtp")
public class EmailProperty {

    private String auth;
    private String startTlsEnable;
    private String host;
    private String port;

}
