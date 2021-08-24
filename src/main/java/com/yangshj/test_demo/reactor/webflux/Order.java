package com.yangshj.test_demo.reactor.webflux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

//    @Id
    private String orderId;
    private String orderNumber;
    private String deliveryAddress;
    private String goods;
}
