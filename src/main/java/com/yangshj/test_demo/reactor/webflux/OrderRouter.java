package com.yangshj.test_demo.reactor.webflux;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

//@Configuration
public class OrderRouter {

    public RouterFunction<ServerResponse> router(OrderHandler orderHandler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/order/{orderNumber")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                orderHandler::getOrderByOrderNumber
        );
    }
}
