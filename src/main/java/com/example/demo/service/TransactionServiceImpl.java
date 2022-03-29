package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionType;
import com.example.demo.service.api.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Service
public class TransactionServiceImpl extends _BaseService implements TransactionService {

    @Override
    @Transactional
    public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
        Account sourceAccount = em.find(Account.class, fromId , LockModeType.PESSIMISTIC_WRITE);
        Account recipientAccount = em.find(Account.class, toId ,LockModeType.PESSIMISTIC_WRITE);

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));

        LocalTime localTime = LocalTime.now();
        em.persist(new Transaction(sourceAccount, recipientAccount, amount, localTime, TransactionType.Debit));
        em.persist(new Transaction(recipientAccount, sourceAccount, amount, localTime, TransactionType.Credit));
    }

    public List<Transaction> listAllTransactions(Long searchById) {
        return  em.createQuery("select t from Transaction t where t.sourceAccount.id = :pSearchById", Transaction.class)
                .setParameter("pSearchById" , searchById).setMaxResults(100).getResultList();
    }
}
