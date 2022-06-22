package com.controller;

import com.config.MyConfig;
import com.config.MyJdbcConfig;
import com.dhlu3.spring3demo.Spring3demoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MyJDBC {
//    @Autowired
//    public MyJdbcConfig mMyJDBC;
//    @Autowired
//    private MyConfig mMyconfig;
    @GetMapping("/myjdbc")
    public String myJDBC(){
        //getApplicationContext();
        //String str = mMyJDBC.url +" "+mMyJDBC.dbname+" "+mMyJDBC.acc+" "+mMyJDBC.pwd;
        //return str;
        return "111";
        //return mMyconfig.uris + mMyJDBC.dbname;
    }
}
