package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.service.api.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceImplTest {

    @Autowired
    TransactionService transactionService;

    @Test
    void transferMoney() {
        transactionService.transferMoney(1L , 4L , BigDecimal.valueOf(10));
        Transaction transaction = transactionService.getEntityManager()
                .createQuery("select t from Transaction t where t.recipientAccount.id = 4 and t.sourceAccount.id = 1", Transaction.class).getSingleResult();
        assertThat(transaction).isNotEqualTo(null);
    }

    @Test
    void listAllTransactions() {
    }
}