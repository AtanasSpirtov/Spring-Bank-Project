package com.example.demo.service.searchEngine;

import com.example.demo.model.Account;
import com.example.demo.service._BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchEngineServiceImpl extends _BaseService implements SearchEngineService {

    @Override
    public List<Account> searchAccountByName(String name) {
        List<Account> accountsInDatabase =
                em.createQuery("select accounts from Account accounts" , Account.class).getResultList();
        List<Account> possibleAccounts = new ArrayList<>();

        accountsInDatabase.parallelStream().forEach(account -> {
            int wordMistakes = 0;
            for (int j = 0; j < account.getName().length(); j++) {
                if (name.length() != account.getName().length()) {
                    //makes name not to be added to possibleAccounts
                    wordMistakes = 3;
                    break;
                }
                if (account.getName().charAt(j) != name.charAt(j) && wordMistakes++ >= 2) {
                    break;
                }
            }
            if (wordMistakes <= 2) {
                possibleAccounts.add(account);
            }
        });
        return possibleAccounts;
    }
}
