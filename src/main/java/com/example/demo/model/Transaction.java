package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;


@Entity
public class Transaction extends _BaseEntity {

    @ManyToOne
    private Account recipientAccount;

    @ManyToOne
    private Account sourceAccount;

    private BigDecimal transactionAmount;

    private LocalTime transactionTime;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public Transaction(Account sourceAccount , Account recipientAccount, BigDecimal transactionAmount, LocalTime transactionTime, TransactionType transactionType) {
        this.sourceAccount = sourceAccount;
        this.recipientAccount = recipientAccount;
        this.transactionAmount = transactionAmount;
        this.transactionTime = transactionTime;
        this.transactionType = transactionType;
    }

    public Transaction() {
        //nothing here
    }

    public String transactionToString() {
        return new StringBuilder(1000)
                .append(sourceAccount.getName()).append(" => ")
                .append(recipientAccount.getName()).append(" : ")
                .append(transactionAmount.toString()).append(" : ")
                .append(transactionTime.toString()).append(" : ")
                .append(transactionType.toString()).append("\n").toString();
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
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

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
