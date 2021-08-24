package com.yangshj.test_demo.reactor.redis;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRedisRepository {

    Mono<Boolean> saveAccount(Account account);

    Mono<Boolean> updateAccount(Account account);

    Mono<Boolean> deleteAccount(String accountId);

    Mono<Account> findAccountById(String accountId);

    Flux<Account> findAllAccounts();
}
