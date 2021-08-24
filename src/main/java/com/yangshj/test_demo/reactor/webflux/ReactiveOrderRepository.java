package com.yangshj.test_demo.reactor.webflux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ReactiveOrderRepository extends ReactiveMongoRepository<Order, String> {

    Mono<Order> getOrderByOrderNumber(String orderNumber);
}
