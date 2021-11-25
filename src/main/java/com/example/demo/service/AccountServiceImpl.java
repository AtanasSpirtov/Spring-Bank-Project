package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.service.api.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Service("WithProxy")
public class AccountServiceImpl extends _BaseService implements AccountService {

    @Transactional
    @Override
    public void createAccount(String name, BigDecimal balance) {
        em.persist(new Account(name, balance));
    }

    @Transactional
    @Override
    public void deleteAccount(Long id) {
        em.remove(id);
    }

    @Override
    public Account findById(Long searchAccById) {
        return em.find(Account.class, searchAccById);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

}
