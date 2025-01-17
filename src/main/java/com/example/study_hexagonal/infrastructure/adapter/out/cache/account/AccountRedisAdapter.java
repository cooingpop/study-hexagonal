package com.example.study_hexagonal.infrastructure.adapter.out.cache.account;

import com.example.study_hexagonal.domain.account.Account;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountRedisAdapter {

    private final RedisTemplate<String, Account> redisTemplate;

    public AccountRedisAdapter(RedisTemplate<String, Account> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheAccount(Account account) {
        redisTemplate.opsForValue().set("account:" + account.getId(), account);
    }

    public Account getCachedAccount(String accountId) {
        return redisTemplate.opsForValue().get("account:" + accountId);
    }
}