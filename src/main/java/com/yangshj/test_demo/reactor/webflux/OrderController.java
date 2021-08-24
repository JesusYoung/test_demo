package com.yangshj.test_demo.reactor.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

    @Autowired
    private StubOrderService orderService;

    @GetMapping("")
    public Flux<Order> getOrders() {
        return this.orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Mono<Order> getOrderById(@PathVariable("id") final String id) {
        return this.orderService.getOrdersById(id);
    }

    @PostMapping("")
    public Mono<Void> createOrder(@RequestBody final Mono<Order> order) {
        return this.orderService.createOrUpdateOrder(order);
    }

    @DeleteMapping("/{id}")
    public Flux<Order> delete(@PathVariable("id") final String id) {
        return this.orderService.getOrders();
    }




}
