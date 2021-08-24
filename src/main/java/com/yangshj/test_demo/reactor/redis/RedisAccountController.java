package com.yangshj.test_demo.reactor.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "redis")
public class RedisAccountController {

    @Autowired
    private RedisAccountService accountService;

    @GetMapping(value = "/account/{accountId}")
    public Mono<Account> getAccountById(@PathVariable("accountId") String accountId) {
        return accountService.findAccountById(accountId);
    }

    @GetMapping(value = "/account")
    public Flux<Account> getAllAccounts() {
        return accountService.findAllAccounts();
    }

    @PostMapping(value = "/account")
    public Mono<Boolean> saveAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @DeleteMapping(value = "/account/{accountId}")
    public Mono<Boolean> deleteAccountById(@PathVariable("accountId") String accountId) {
        return accountService.delete(accountId);
    }
}
