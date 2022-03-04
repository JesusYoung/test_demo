package com.yangshj.test_demo.annotation.import_annotation;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Create by yangshijie on 3/4/22
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ImportTestBean.class);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        registry.registerBeanDefinition("ImportTestBean", beanDefinition);
    }

//    // 或者 使用如下的方法也可以，自动生成beanName
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
//        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ImportTestBean.class);
//        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
//        String beanName = importBeanNameGenerator.generateBeanName(beanDefinition, registry);
//        registry.registerBeanDefinition(beanName, beanDefinition);
//    }
}
