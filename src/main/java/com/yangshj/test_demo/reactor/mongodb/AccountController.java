package com.yangshj.test_demo.reactor.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping(value = "/{accountId}")
    public Mono<Account> getAccountById(@PathVariable("accountId") String accountId) {
        Mono<Account> account = accountService.getAccountById(accountId);
        return account;
    }

    @GetMapping(value = "accountname/{accountName}")
    public Mono<Account> getAccountByAccountName(@PathVariable("accountName") String accountName) {

        Mono<Account> account = accountService.getAccountByAccountName(accountName);
        return account;
    }

//    @PostMapping(value = "/")
//    public Mono<Void> addAccount(@RequestBody Mono<Account> account) {
//
//        return accountService.addAccount(account);
//    }

//    @PutMapping(value = "/")
//    public Mono<Void> updateAccount(@RequestBody Mono<Account> account) {
//
//        return accountService.updateAccount(account);
//    }
}
