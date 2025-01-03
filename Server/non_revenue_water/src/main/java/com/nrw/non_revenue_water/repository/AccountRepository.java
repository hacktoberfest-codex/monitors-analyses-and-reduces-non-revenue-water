package com.nrw.non_revenue_water.repository;

import java.util.Optional;

// import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.nrw.non_revenue_water.model.Account;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import com.nrw.non_revenue_water.constant.AccountType;


public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNumber(long accountNumber);
    Optional<Account> findByAccountType(AccountType accountType);

    // Named Parameters
    // Native Query:-
    // @Query(nativeQuery = true, value = "SELECT * FROM account_table where
    // account_email=?")
    // JPSQL:-
    @Query("SELECT a FROM Account a WHERE a.credential.accountEmail = :email")
    Optional<Account> findByEmail(@PathParam("email") String email);
    // We can also pass method parameters to the query using named parameters. We
    // define these using the @Param annotation inside our repository method
    // declaration.

    // Each parameter annotated with @Param must have a value string matching the
    // corresponding JPQL or SQL query parameter name. Refer above findByEmail
    // method

    @Query("UPDATE Account a SET a.accountBalance = a.accountBalance + :balance WHERE" +
    " a.accountNumber = :accountNumber")
    @Modifying
    @Transactional
    int addBalance(@PathParam("accountNumber") long accountNumber, @PathParam("balance") double balance);

}
