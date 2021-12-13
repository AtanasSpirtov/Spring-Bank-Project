package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.service.api.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    @Qualifier("WithProxy")
    AccountService accountService;

    @Test
    void createAccount() {
        accountService.createAccount("Ico", BigDecimal.valueOf(200));
        Account account = accountService.getEntityManager().createQuery("select a from Account a where a.name =: pName", Account.class)
                .setParameter("pName", "Ico").getSingleResult();
        assertThat(account).isNotEqualTo(null);
    }

    @Test
    void deleteAccount() {
        Account account = accountService.deleteAccount("Ico");
        assertThat(account).isEqualTo(null);
    }

    @Test
    void findById() {
        Account account = accountService.findById(1L);
        assertThat(account.getName()).isEqualTo("stancho");
    }
}