package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.service.api.AccountService;
import com.example.demo.service.api.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TransactionServiceImplTest {

    @Autowired
    TransactionService transactionService;

    @Autowired
    @Qualifier("WithProxy")
    AccountService accountService;

    @Test
    @Transactional
    void transferMoney() {
        transactionService.transferMoney(1L, 4L, BigDecimal.valueOf(10));
        Transaction creditTransaction = transactionService.getEntityManager()
                .createQuery("select t from Transaction t where t.recipientAccount.id = 4 and t.sourceAccount.id = 1", Transaction.class).getSingleResult();
        Transaction debitTransaction = transactionService.getEntityManager()
                .createQuery("select t from Transaction t where t.recipientAccount.id = 1 and t.sourceAccount.id = 4", Transaction.class).getSingleResult();
        assertThat(creditTransaction).isNotNull();
        assertThat(debitTransaction).isNotNull();
        transactionService.getEntityManager().remove(creditTransaction);
        transactionService.getEntityManager().remove(debitTransaction);
    }

//    @Test
//    void listAllTransactions() {
//        Account source = accountService.findById(1L);
//        Account recipient = accountService.findById(2L);
//        assertThat(transactionService.listAllTransactions(1L)).isEqualTo(Stream.of(
//                new Transaction(source , recipient , BigDecimal.valueOf(30) , new LocalDateTime()) , new Transaction()));
//    }
}