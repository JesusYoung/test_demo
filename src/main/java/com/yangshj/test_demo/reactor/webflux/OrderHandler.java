package com.yangshj.test_demo.reactor.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

//@Configuration
public class OrderHandler {

    @Autowired
    private OrderService orderService;

    public Mono<ServerResponse> getOrderByOrderNumber(ServerRequest request) {
        String orderNumber = request.pathVariable("orderNumber");

        return ServerResponse.ok().body(this.orderService.getOrderByOrderNumber(orderNumber), Order.class);
    }
}
