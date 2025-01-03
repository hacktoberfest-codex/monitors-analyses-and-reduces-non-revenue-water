package com.nrw.non_revenue_water.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nrw.non_revenue_water.model.Account;
import com.nrw.non_revenue_water.model.Transaction;
import com.nrw.non_revenue_water.repository.AccountRepository;
import com.nrw.non_revenue_water.repository.TransactionRepository;
import com.nrw.non_revenue_water.service.ITransactionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionImpl implements ITransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Async
    @Transactional
    @Override
    public void addTransaction(Transaction transaction, long accountNumber) {
        var account = getAccount(accountNumber);
        transaction.setAccount(account);

        var transactions = account.getTransactions();
        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        transaction.setTimestamp(LocalDateTime.now());
        transactions.add(transaction);

        transactionRepository.save(transaction);
    }

    private Account getAccount(long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow();
    }
}
