package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class Account extends _BaseEntity {

    private String name;

    private BigDecimal balance;

    public Account(String name, BigDecimal balance) {
        super();
        this.name = name;
        this.balance = balance;
    }

    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }

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
