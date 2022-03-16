package com.example.demo.service.searchEngine;

import com.example.demo.model.Account;

import java.util.List;

public interface SearchEngineService {
    List<Account> searchAccountByName(String word);
}
