package com.nrw.non_revenue_water.service;

import java.util.concurrent.CompletableFuture;

import com.nrw.non_revenue_water.model.Account;

public interface IAccountService {
    // Create an account without username
    Account createAccount(Account account);

    Account getAccountByEmail(String email);

    CompletableFuture<Double> getAccountBalanace(long accountNumber);

    CompletableFuture<Integer> getNumberOfComplaints(long accountNumber);

}
