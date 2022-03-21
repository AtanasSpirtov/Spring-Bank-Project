package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.service.api.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

@Service
public class AccountServiceImpl extends _BaseService implements AccountService {

    @Transactional
    @Override
    public void createAccount(String name, BigDecimal balance) {
        em.persist(new Account(name, balance));
    }

    @Transactional
    @Override
    public Account deleteAccount(String name) {
        Account account = em.createQuery("select a from Account a where a.name =: pName" , Account.class)
                .setParameter("pName" , name)
                .getSingleResult();

        em.createQuery("delete from Account a where a.name=: pName")
                .setParameter("pName" , name)
                .executeUpdate();
        return account;
    }

    @Override
    public Account findById(Long searchAccById) {
        return em.find(Account.class, searchAccById , PESSIMISTIC_WRITE);
    }

    @Override
    public Account findByName(String accountName) {
        return em.createQuery("select a from Account a where a.name=:pName " , Account.class)
                .setParameter("pName" , accountName)
                .getSingleResult();
    }
}
