package com.yangshj.test_demo.reactor.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component
public class InitDatabase {

//    @Bean
    CommandLineRunner init(MongoOperations operations) {
        return args -> {
            operations.dropCollection(Account.class);

            operations.insert(new Account("A_" + UUID.randomUUID().toString(), "account1", "ysj1"));
            operations.insert(new Account("A_" + UUID.randomUUID().toString(), "account2", "ysj2"));

            operations.findAll(Account.class).forEach(
                    account -> System.out.println(account.getId())
            );
        };
    }
}
