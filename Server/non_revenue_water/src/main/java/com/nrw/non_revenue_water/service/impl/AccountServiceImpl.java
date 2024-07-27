package com.nrw.non_revenue_water.service.impl;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.NoSuchElementException;
import java.util.Optional;
// import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Value("${upload.file.name}")
    private String uploadFileLocation;


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

    // @Async
    @Override
    public Optional<Account> getAccountBalanaceAndNumberOfComplaints(long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public long countRecord() {
        return accountRepository.count();
    }

    // @Override
    // public List<Complaint> getAllComplaints() {
    //     return complaintRepository.findAll();
    // }

    @Override
    public Account getAccount(long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow();
    }

     @Override
    public Account uploadProfilePicture(long accountNumber, MultipartFile file) throws Exception {
        var account = getAccount(accountNumber);

        var fileName = file.getOriginalFilename();
        var extensionName = fileName.substring(fileName.lastIndexOf('.'));
        var name = fileName.substring(0, fileName.lastIndexOf('.'));
        var newName = uploadFileLocation + name + "-" + System.currentTimeMillis() + extensionName;

        var fos = new FileOutputStream(newName);
        fos.write(file.getBytes());

        account.setProfilePicture(newName);
        accountRepository.save(account);

        fos.close();
        return account;
    }

    @Override
    public byte[] getProfilePicture(long accountNumber) throws Exception {
        var account = getAccount(accountNumber);
        var fileLocation = account.getProfilePicture();
        if (fileLocation == null)
            throw new NoSuchElementException("Image not present");

        var fis = new FileInputStream(fileLocation);
        var image = fis.readAllBytes();
        fis.close();

        return image;
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
