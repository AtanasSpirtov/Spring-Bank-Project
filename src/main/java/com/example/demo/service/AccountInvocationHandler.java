package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.service.api.AccountService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AccountInvocationHandler implements InvocationHandler {
    private static final int CACHE_SIZE = 50;

    private Map<Long, Account> cache;

    private final AccountService accountService;

    public AccountInvocationHandler(AccountService accountService) {
        this.accountService = accountService;

        cache = new HashMap<>(CACHE_SIZE);

        List<Account> list = accountService.getEntityManager().createQuery("select a from Account a" , Account.class).
                setMaxResults(CACHE_SIZE).getResultList();

        cache = list.parallelStream().collect(Collectors.toMap(Account::getId , Function.identity()));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if ("findById".equals(method.getName())) {
            return cache.containsKey(args[0]) ? cache.get(args[0]) : accountService.findById((Long) args[0]);
        }
        else if ("deleteAccount".equals(method.getName())) {
            Account deletedAccount = accountService.deleteAccount((String) args[0]);
            if (cache.containsKey(deletedAccount.getId())) {
                cache.remove(deletedAccount.getId());
            }
            return null;
        }
        return method.invoke(accountService, args);
    }
}
