package com.nrw.non_revenue_water.model;

import java.time.LocalDateTime;

import com.nrw.non_revenue_water.constant.TransactionMode;
import com.nrw.non_revenue_water.model.helper.Auditing;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionMode mode;
    private LocalDateTime timestamp;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_sl_no")
    private Account account;

}
