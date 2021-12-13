package com.example.demo.service.api;

import com.example.demo.model.Account;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
public interface AccountService {
    void createAccount(String username , BigDecimal balance);
    Account deleteAccount(String name);
    Account findById(Long id);
    EntityManager getEntityManager();

    Account findByName(String accountName);
}
