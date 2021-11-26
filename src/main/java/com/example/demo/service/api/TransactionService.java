package com.example.demo.service.api;

import com.example.demo.model.Transaction;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    void transferMoney(Long from, Long to, BigDecimal amount);

    List<Transaction> listAllTransactions(Long searchById);

    EntityManager getEntityManager();

}
