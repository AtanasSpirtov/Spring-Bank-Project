package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.service.api.AccountService;
import com.example.demo.service.api.TransactionService;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaDescriptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceImplTest {

    @Autowired
    TransactionService transactionService;

    @Autowired
    @Qualifier("WithProxy")
    AccountService accountService;

    @Test
    void transferMoney() {
        transactionService.transferMoney(1L, 4L, BigDecimal.valueOf(10));
        Transaction transaction = transactionService.getEntityManager()
                .createQuery("select t from Transaction t where t.recipientAccount.id = 4 and t.sourceAccount.id = 1", Transaction.class).getSingleResult();
        assertThat(transaction).isNotEqualTo(null);
    }

//    @Test
//    void listAllTransactions() {
//        Account source = accountService.findById(1L);
//        Account recipient = accountService.findById(2L);
//        assertThat(transactionService.listAllTransactions(1L)).isEqualTo(Stream.of(
//                new Transaction(source , recipient , BigDecimal.valueOf(30) , new LocalDateTime()) , new Transaction()));
//    }
}