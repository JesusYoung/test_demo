package com.yangshj.test_demo.reactor.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface ReactiveAccountRepository extends ReactiveMongoRepository<Account, String>, ReactiveQueryByExampleExecutor<Account> {

    Mono<Account> findAccountByAccountName(String accountName);
}
