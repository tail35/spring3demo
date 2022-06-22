package com.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "myjdbc")
public class MyJdbcConfig {
    public String driverClassName;
    public String url;
    public String dbname;
    public String acc;
    public String pwd;
}
