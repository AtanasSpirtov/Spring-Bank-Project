package com.example.demo.controller.dto;

import java.math.BigDecimal;

public class AccountDTO {
    private Long id;

    private String name;

    private BigDecimal balance;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal amount) {
        this.balance = amount;
    }
}
