package com.example.demo.controller.dto;

import com.example.demo.model.Account;

import java.math.BigDecimal;

public class TransactionDTO {

    private Account recipientAccount;

    private Account sourceAccount;

    private BigDecimal transactionAmount;

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
}
