package com.example.demo;

import com.example.demo.service.AccountInvocationHandler;
import com.example.demo.service.AccountServiceImpl;
import com.example.demo.service.api.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.lang.reflect.Proxy;

@Configuration
public class BankConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean(name = "WithProxy")
    public AccountService withProxy( AccountServiceImpl accountService) {
        AccountInvocationHandler accountInvocationHandler = new AccountInvocationHandler(accountService);
        return (AccountService) Proxy.newProxyInstance(AccountServiceImpl.class.getClassLoader(), new Class[]{AccountService.class}, accountInvocationHandler);
    }
}

