package com.example.demo.controller.dto;

import java.math.BigDecimal;

public class TransactionDTO {

    private AccountDTO sourceAccount;

    private AccountDTO recipientAccount;

    private BigDecimal transactionAmount;

    public AccountDTO getSourceAccount() {return sourceAccount;}

    public void setSourceAccount(AccountDTO sourceAccount) {this.sourceAccount = sourceAccount;}

    public AccountDTO getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(AccountDTO recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
