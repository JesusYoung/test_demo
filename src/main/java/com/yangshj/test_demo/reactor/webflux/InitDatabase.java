package com.yangshj.test_demo.reactor.webflux;

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
            operations.dropCollection(Order.class);

            operations.insert(new Order("O_" + UUID.randomUUID().toString(), "Order001", "deliveryAddress1", ""));
            operations.insert(new Order("O_" + UUID.randomUUID().toString(), "Order002", "deliveryAddress2", ""));

            operations.findAll(Order.class).forEach(
                    order -> System.out.println(order.getOrderId())
            );
        };
    }
}
