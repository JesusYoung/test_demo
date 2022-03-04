package com.yangshj.test_demo.annotation.import_annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by yangshijie on 3/4/22
 */
public class ImportTest {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ac.start();

        ImportTestBean bean = ac.getBean(ImportTestBean.class);
        System.out.println("bean: " + bean != null);
        System.out.println("bean: " + bean.toString());
    }
}
