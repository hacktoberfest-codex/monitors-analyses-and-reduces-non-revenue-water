package com.nrw.non_revenue_water.service;

import java.io.FileNotFoundException;
import java.util.Optional;
// import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

import com.nrw.non_revenue_water.model.Account;

public interface IAccountService {
    // Create an account without username
    Account createAccount(Account account);

    Account getAccountByEmail(String email);

    Account getAccount(long accountNumber);

    long countRecord();

    Optional<Account> getAccountBalanaceAndNumberOfComplaints(long accountNumber);

    // CompletableFuture<Integer> getNumberOfComplaints(long accountNumber);

    Account uploadProfilePicture(long accountNumber, MultipartFile file) throws FileNotFoundException, Exception;

    byte[] getProfilePicture(long accountNumber) throws Exception;

    

}
