package com.yangshj.test_demo.reactor.rsockect;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class RSocketController {


    @Autowired
    RSocketRequester.Builder builder;


//    @MessageMapping("hello")
    public Mono<String> hello(String input) {
        return Mono.just("Hello: " + input);
    }




//    RSocketRequester requester = builder.dataMimeType(MimeTypeUtils.TEXT_PLAIN)
//            .connect(TcpClientTransport.create(7000)).block();



//    Flux<Message> stream(Message request) {
//        return Flux.interval(Duration.ofSeconds(1))
//                .map(index -> new Message(request.getParam, index));
//    }

}
