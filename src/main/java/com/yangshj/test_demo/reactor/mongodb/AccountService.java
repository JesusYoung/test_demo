package com.yangshj.test_demo.reactor.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {


    @Autowired
    private ReactiveAccountRepository accountRepository;


    public Mono<Account> getAccountById(String accountId) {

        return accountRepository.findById(accountId).log("getAccountById");
    }

    public Mono<Account> getAccountByAccountName(String accountName) {

        return accountRepository.findAccountByAccountName(accountName).log("getAccountByAccountName");
    }

//    public Mono<Void> addAccount(Mono<Account> account){
//
//        Mono<Account> saveAccount = account.flatMap(accountRepository::save);
//
//        return saveAccount.flatMap(accountChangedSource::publishAccountUpdatedEvent);
//    }

//    public Mono<Void> updateAccount(Mono<Account> account){
//
//        Mono<Account> saveAccount = account.flatMap(accountRepository::save);
//
//        return saveAccount.flatMap(accountChangedSource::publishAccountUpdatedEvent);
//    }

    public Flux<Account> getAccountsByAccountName(String accountName) {
        Account account = new Account();
        account.setAccountName(accountName);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher(accountName, ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.STARTING))
                .withIncludeNullValues();

        Example<Account> example = Example.of(account, matcher);

        return accountRepository.findAll(example).log("getAccountsByAccountName");
    }
}
