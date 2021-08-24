package com.yangshj.test_demo.reactor.mongodb;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//@Configuration
//@EnableReactiveMongoRepositories(basePackageClasses = ReactiveAccountRepository.class)
public class MongoConfig extends AbstractReactiveMongoConfiguration {

//    @Bean
//    @Override
//    public MongoClient reactiveMongoClient() {
//        return MongoClients.create();
//    }
//
    @Override
    protected String getDatabaseName() {
        return "order_test";
    }
//
//    @Bean
//    public ReactiveMongoTemplate mongoTemplate() throws Exception {
//        return new ReactiveMongoTemplate(mongoClientSettings(), getDatabaseName());
//    }
}
