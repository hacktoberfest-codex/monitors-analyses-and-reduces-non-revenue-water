package com.nrw.non_revenue_water.model;

import java.util.List;

import com.nrw.non_revenue_water.constant.AccountType;
import com.nrw.non_revenue_water.model.helper.Auditing;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "account_table")
public class Account extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountSlno;
    @Column(unique = true, nullable = false)
    private long accountNumber;
    @Column(name = "account_name", nullable = false)
    private String accountHolderName;
    @Column(nullable = false)
    private String contactNumber;
    private String profilePicture;
    @Lob
    private String aboutCustomer;
    private double accountBalance;
    @Column(name = "complaints")
    private int numberOfComplaints;
    @Embedded
    private Credential credential;
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private AdminAccount adminAccount;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
