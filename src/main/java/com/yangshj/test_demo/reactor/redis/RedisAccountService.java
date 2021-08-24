package com.yangshj.test_demo.reactor.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RedisAccountService {

    @Autowired
    private AccountRedisRepository accountRedisRepository;

    public Mono<Boolean> save(Account account) {
        return accountRedisRepository.saveAccount(account);
    }

    public  Mono<Boolean> delete(String id) {
        return accountRedisRepository.deleteAccount(id);
    }

    public Mono<Account> findAccountById(String id) {
        return accountRedisRepository.findAccountById(id).log("findOnAccount");
    }

    public Flux<Account> findAllAccounts() {
        return accountRedisRepository.findAllAccounts().log("findAllAccounts");
    }
}
