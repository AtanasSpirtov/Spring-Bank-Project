package com.example.demo.controller;

import com.example.demo.service.api.SecurityService;
import com.example.demo.service.searchEngine.SearchEngineService;
import com.example.demo.service.api.AccountService;
import com.example.demo.service.api.TransactionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class _BaseController {

    @Autowired
    protected TransactionService transactionService;

    protected static Logger logger = LoggerFactory.getLogger("Controller Layer");

    @Qualifier("WithProxy")
    @Autowired
    protected AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SearchEngineService searchEngineService;

    @Autowired
    SecurityService userService;
}
