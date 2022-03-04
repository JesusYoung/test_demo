package com.yangshj.test_demo.annotation.import_annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Create by yangshijie on 3/4/22
 */
@Configuration
// 测试普通实体类注入
//@Import(ImportTestBean.class)

// 测试实现ImportSelector接口的注入
//@Import(MyImportSelector.class)

// 测试实现ImportBeanDefinitionRegistrar接口的注入
@Import(MyImportBeanDefinitionRegistrar.class)
public class MyConfigure {
}
