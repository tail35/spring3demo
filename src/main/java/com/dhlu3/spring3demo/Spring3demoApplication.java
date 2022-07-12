package com.dhlu3.spring3demo;


import com.config.MyConfig;
import com.config.MyJdbcConfig;
import com.config.MyXMLBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.sql.*;

@EnableScheduling
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) //not auto config db.
//@SpringBootApplication
@ComponentScan("com.controller")//load to main run,then you can use multi controler class.
@ComponentScan("com.dhlu3.spring3demo")
@Component

@EnableConfigurationProperties(
   {MyJdbcConfig.class,MyConfig.class}//looks like write a line  will no error.
)

public class Spring3demoApplication {
    public static Spring3demoApplication This;
    static public MyXMLBean obj;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    @Autowired
    public MyJdbcConfig mMyJDBC;
    @Autowired
    private MyConfig mMyconfig;
//    public Spring3demoApplication()
//    {
//        This = this;
//        Spring3demoApplication.This.InitMydb();
//    }
    /*
    当您从类App的构造函数调用init方法时，Spring尚未将依赖项自动关联到App对象中.
    如果要在Spring完成创建和自动装配App对象后调用此方法，
    请添加带有@PostConstruct批注的方法来执行此操作
     */
    @PostConstruct
    void InitMydb()//InitMydb 在Spring3demoApplication 调用，mMyconfig 会是空。因为还没有初始化装配。
    {
        //第一步，注册驱动程序
        //com.MySQL.jdbc.Driver
        try {
            Class.forName(mMyJDBC.driverClassName);
        }catch (java.lang.ClassNotFoundException e)
        {
            System.out.println(e.toString());
        }
        try {
            //第二步，获取一个数据库的连接
            conn = DriverManager.getConnection(mMyJDBC.url, mMyJDBC.acc, mMyJDBC.pwd);
        }catch (SQLException e)
        {
            System.out.println(e.toString());
            String str = e.toString();
        }
        //test
        QuerySql();
    }
    String QuerySql()
    {
        String str="";
        try {
            //第三步，创建一个会话
            stmt = conn.createStatement();
            //第四步，执行SQL语句
            //stmt.executeUpdate("SQL语句");
            //或者查询记录
            rs = stmt.executeQuery("select * from name");
            JSONObject jsonObject = new JSONObject();

            while (rs.next()) {
                String acc = rs.getString("acc");
                String pwd = rs.getString("pwd");
                try {
                    jsonObject.put("acc", acc);
                    jsonObject.put("pwd", pwd);
                }catch (JSONException e)
                {
                    System.out.println("jse:" + e.toString());
                }
            }
            str= jsonObject.toString();
        }catch (SQLException e)
        {

        }
        return "";
    }
    void CloseDb()
    {
        try{
            //第六步，关闭连接
            rs.close();
            stmt.close();
            conn.close();
        }catch (SQLException e)
        {

        }
    }
    public static void main(String[] args) {

        try {
            //laod xml config as bean
            File resDir = new File("src/main/resources");
            String beanDir = resDir.getCanonicalPath()+"/Beans.xml";

            System.out.println("3main:" + beanDir);

            ApplicationContext context = new FileSystemXmlApplicationContext(beanDir);
            obj = (MyXMLBean) context.getBean("MyXMLBean");//xml id
            String resstr =  obj.getMessage();
            System.out.println("3main:" + resstr);

            //

        }catch (IOException e){

        }
        SpringApplication.run(Spring3demoApplication.class, args);
    }

}
