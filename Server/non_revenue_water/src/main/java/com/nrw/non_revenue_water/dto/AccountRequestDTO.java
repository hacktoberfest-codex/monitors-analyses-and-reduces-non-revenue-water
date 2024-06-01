package com.nrw.non_revenue_water.dto;

import com.nrw.non_revenue_water.constant.AccountType;

import jakarta.validation.constraints.NotNull;

// Used both for admin and user account
public record AccountRequestDTO( // DTO-Data Tranfer Object
        @NotNull(message = "Account type should not be Null") AccountType accountType,
        @NotNull(message = "Account holder name should not be Null") String accountHolderName,
        @NotNull(message = "Account email should not be Null") String accountEmail,
        @NotNull(message = "Account password should not be Null") String accountPassword,
        @NotNull(message = "Contact number should not be Null") String contactNumber,
        @NotNull(message = "State should not be Null") String state,
        @NotNull(message = "City should not be Null") String city,
        @NotNull(message = "Area should not be Null") String area,
        String zipcode) {

}
// use this dependency in pom.xml to import @NotNull
// <dependency>
// <groupId>org.springframework.boot</groupId>
// <artifactId>spring-boot-starter-validation</artifactId>
// </dependency>