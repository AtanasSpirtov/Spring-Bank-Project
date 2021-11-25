package com.example.demo.service.api;

import com.example.demo.model.Account;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
public interface AccountService {
    void createAccount(String username , BigDecimal balance);
    void deleteAccount(Long name);
    Account findById(Long id);
    EntityManager getEntityManager();
}
