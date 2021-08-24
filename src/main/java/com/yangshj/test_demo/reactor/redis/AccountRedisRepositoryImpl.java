package com.yangshj.test_demo.reactor.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Repository
public class AccountRedisRepositoryImpl implements AccountRedisRepository {

    @Resource
    private ReactiveRedisTemplate<String, Account> reactiveRedisTemplate;

    private static final String HASH_NAME = "Account:";

    @Override
    public Mono<Boolean> saveAccount(Account account) {
        return reactiveRedisTemplate.opsForValue().set(HASH_NAME + account.getId(), account);
    }

    @Override
    public Mono<Boolean> updateAccount(Account account) {
        return reactiveRedisTemplate.opsForValue().set(HASH_NAME + account.getId(), account);
    }

    @Override
    public Mono<Boolean> deleteAccount(String accountId) {
        return reactiveRedisTemplate.opsForValue().delete(HASH_NAME + accountId);
    }

    @Override
    public Mono<Account> findAccountById(String accountId) {
        return reactiveRedisTemplate.opsForValue().get(HASH_NAME + accountId);
    }

    @Override
    public Flux<Account> findAllAccounts() {
        return reactiveRedisTemplate.keys(HASH_NAME + "*").flatMap((String key)
                -> reactiveRedisTemplate.opsForValue().get(key));
    }
}
