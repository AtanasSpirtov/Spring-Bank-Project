package com.example.demo;

import com.example.demo.service.AccountInvocationHandler;
import com.example.demo.service.AccountServiceImpl;
import com.example.demo.service.UserSignServiceImpl;
import com.example.demo.service.api.AccountService;
import com.example.demo.service.api.UserSignService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Proxy;

@Configuration
public class BankConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean(name = "WithProxy")
    public AccountService withProxy(AccountServiceImpl accountService) {
        AccountInvocationHandler accountInvocationHandler = new AccountInvocationHandler(accountService);
        return (AccountService) Proxy.newProxyInstance(AccountServiceImpl.class.getClassLoader(), new Class[]{AccountService.class}, accountInvocationHandler);
    }
    @Bean
    public UserSignService userService() {
        return new UserSignServiceImpl();
    }
}

