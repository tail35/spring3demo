package com.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "elasticsearch.rest")
public class MyConfig {
    public String uris;
    public String username;
    public String password;
}
