package com.controller;

import com.config.MyjdbcClickHouseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MyClickHouse {
    @Autowired
    MyjdbcClickHouseConfig myjdbcClickHouseConfig;
    @GetMapping("/clickhouse")
    public String clickhouse(){
        return "clickhouse";
    }
}
