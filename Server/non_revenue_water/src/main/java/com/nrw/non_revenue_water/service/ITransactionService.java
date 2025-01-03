package com.nrw.non_revenue_water.service;

import com.nrw.non_revenue_water.model.Transaction;

public interface ITransactionService {
    void addTransaction(Transaction transaction, long accountNumber);
}
