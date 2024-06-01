package com.nrw.non_revenue_water.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nrw.non_revenue_water.model.Account;
import com.nrw.non_revenue_water.repository.AccountRepository;
import com.nrw.non_revenue_water.service.IAccountService;
import com.nrw.non_revenue_water.utility.GenerateAccountNumber;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account createAccount(Account account) {
        account.setAccountNumber(GenerateAccountNumber.generate());
        var bcrypt = passwordEncoder.encode(account.getCredential().getAccountPassword());
        account.getCredential().setAccountPassword(bcrypt);

        accountRepository.save(account);
        return account;

    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public CompletableFuture<Double> getAccountBalanace(long accountNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountBalanace'");
    }

    @Override
    public CompletableFuture<Integer> getNumberOfComplaints(long accountNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumberOfComplaints'");
    }

}
// To use password encoder class it is an interface so BCryptPasswordEncoder
// implemnts the passwordEncoder class
// so you have to use @bean in a method of SecurityConfig.java class
// Refer To SecurityConfig class for more understanding
// The method used in SecurityConfig.java class
// @Bean
// PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder(12);
// }
// Also add this to pom.xml to use the feature of passwordEncoder
// <dependency>
// <groupId>org.springframework.boot</groupId>
// <artifactId>spring-boot-starter-security</artifactId>
// </dependency>
