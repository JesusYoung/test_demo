package com.yangshj.test_demo.reactor.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class ReactiveAccountChangeSource {

    private static final Logger logger = LoggerFactory.getLogger(ReactiveAccountChangeSource.class);

//    private FluxSink<Message<AccountChangedEvent>> eventSink;
//    private Flux<Message<AccountChangedEvent>> flux;

//    public ReactiveAccountChangedSource() {
//        this.flux = Flux.<Message<AccountChangedEvent>>create(sink -> this.eventSink = sink).publish().autoConnect();
//    }

//    private Mono<Void> publishAccountChangedEvent(String operation, Account account){
//        logger.debug("Sending message for Account Id: {}", account.getId());
//
//        AccountMessage accountMessage = new AccountMessage(account.getId(), account.getAccountCode(), account.getAccountName());
//
//        AccountChangedEvent originalevent =  new AccountChangedEvent(
//                AccountChangedEvent.class.getTypeName(),
//                operation,
//                accountMessage);
//
//        Mono<AccountChangedEvent> monoEvent = Mono.just(originalevent);
//
//        return monoEvent.map(event -> eventSink.next(MessageBuilder.withPayload(event).build())).then();
//    }
//
//    @StreamEmitter
//    public void emit(@Output(Source.OUTPUT) FluxSender output) {
//        output.send(this.flux);
//    }

//    public Mono<Void> publishAccountUpdatedEvent(Account account) {
//        return publishAccountChangedEvent("UPDATE", account);
//    }
//
//    public Mono<Void> publishAccountDeletedEvent(Account account) {
//        return publishAccountChangedEvent("DELETE", account);
//    }
}
