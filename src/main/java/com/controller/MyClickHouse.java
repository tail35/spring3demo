package com.controller;

import com.config.MyjdbcClickHouseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class MyClickHouse {
    public Connection getConn() {

        ClickHouseConnection conn = null;
        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setUser(myjdbcClickHouseConfig.username);
        properties.setPassword(myjdbcClickHouseConfig.password);
        properties.setDatabase(myjdbcClickHouseConfig.dbName);
        properties.setSocketTimeout(myjdbcClickHouseConfig.socketTimeout);
        ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(myjdbcClickHouseConfig.address, properties);
        try {
            conn = clickHouseDataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //public List<Map<String, String>> exeSql(String sql) {
    public  String exeSql(String sql) {
        //log.info("cliockhouse 执行sql：" + sql);
        Connection connection = getConn();
        if(null==connection){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map<String, String>> list = new ArrayList<>();
            JSONArray array = new JSONArray();
            while (results.next()) {
                Map<String, String> row = new HashMap<>();
                JSONObject jsonObject = new JSONObject();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    try {
                        jsonObject.put(rsmd.getColumnName(i), results.getString(rsmd.getColumnName(i)));
                    }catch (JSONException e)
                    {
                        System.out.println("jse:" + e.toString());
                    }
                    row.put(rsmd.getColumnName(i), results.getString(rsmd.getColumnName(i)));
                }
//                try{
                    array.put(jsonObject);
//                }catch (JSONException e)
//                {
//                    System.out.println("jse:" + e.toString());
//                }
                list.add(row);
            }

            //return list;
            String str = array.toString();
            return str;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    MyjdbcClickHouseConfig myjdbcClickHouseConfig;
    @GetMapping("/clickhouse")
    public String clickhouse(){
        String str="select * from t2;";
        exeSql(str);
        return "clickhouse";
    }
}
