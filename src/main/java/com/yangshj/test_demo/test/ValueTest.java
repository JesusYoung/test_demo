package com.yangshj.test_demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

public class ValueTest {

//    @Value("${websocket.constant.ip}")
//    private static String valueA;
//
//    @Value("${websocket.constant.port}")
//    private static int valueB;
//
//
//    @Autowired
//    private ValueConfig valueConfig;
//    private static ValueConfig valueConfig2;
//
//    @PostConstruct
//    public void init() {
//        valueConfig2 = this.valueConfig;
//    }
//
//
//    private void aaa() {
//        System.out.println("valueA: " + valueA);
//        System.out.println("valueB: " + valueB);
//    }
//
//
//    private void bbb() {
//
//        System.out.println(valueConfig2.getIp());
//        System.out.println(valueConfig2.getPort());
//    }

    private void aac() {
        System.out.println("ip: " + ip);
        System.out.println("port: " + port);
    }

    private static String ip;
    private static int port;

    static {
        System.out.println("application.properties属性文件读取开始");
        ClassPathResource resource = new ClassPathResource("application.properties");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            ip = properties.getProperty("websocket.constant.ip");
            port = Integer.parseInt(properties.getProperty("websocket.constant.port"));
            System.out.println("ip的值：" + ip);
            System.out.println("port的值：" + port);
        } catch (IOException e) {
            System.out.println("application.properties属性文件读取异常" + e);
        }
        System.out.println("application.properties属性文件读取完成");
    }


    public static void main(String[] args) {
        ValueTest valueTest = new ValueTest();
//        valueTest.aaa();


        valueTest.aac();



    }
}
