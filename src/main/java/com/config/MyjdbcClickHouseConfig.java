package com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jdbcclickhouse")
public class MyjdbcClickHouseConfig {
    public String     address;
    public String     username;
    public String     password;
    public String     dbName;
    public int     socketTimeout;
}
