package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.service.searchEngine.SearchEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Rollback
class SearchEngineImplTest {

    @Autowired
    SearchEngineService searchEngineService;

    @PersistenceContext
    EntityManager em;

    Account testAccount;

    @BeforeEach
    public void init() {
        testAccount =  em.createQuery("select account from Account  account where account.name =: pName", Account.class)
                .setParameter("pName", "accounts").getSingleResult();
    }


    @Test
    @Transactional
    void searchByKeyWordExact() {
        List<Account> expected = new ArrayList<>();
        expected.add(testAccount);
        assertThat(expected).isEqualTo(searchEngineService.searchAccountByName("accounts"));
    }

    @Test
    @Transactional
    void searchByKeyWordWithOneMistake() {
        List<Account> expected = new ArrayList<>();
        expected.add(testAccount);
        assertThat(expected).isEqualTo(searchEngineService.searchAccountByName("ackounts"));
    }

    @Test
    @Transactional
    void searchByKeyWordWithTwoMistakes() {
        List<Account> expected = new ArrayList<>();
        expected.add(testAccount);
        assertThat(expected).isEqualTo(searchEngineService.searchAccountByName("ackounps"));
    }
    @Test
    @Transactional
    void searchByKeyWordWithManyMistakes()
    {
        List<Account> expected = new ArrayList<>();
        expected.add(testAccount);
        assertThat(searchEngineService.searchAccountByName("akkuunps")).isNotEqualTo(expected);
        assertThat(searchEngineService.searchAccountByName("akkounps")).isNotEqualTo(expected);
    }

    @Test
    @Transactional
    void searchWithEmptyInput()
    {
        List<String> expected = new ArrayList<>();
        assertThat(searchEngineService.searchAccountByName("")).isEqualTo(expected);
    }

}
