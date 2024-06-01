package com.nrw.non_revenue_water.model;

import com.nrw.non_revenue_water.model.helper.Auditing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "adminaccount")
public class AdminAccount extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountId;
    @Column(name = "todays_revenue")
    private double todaysRevenue;
    @Column(name = "total_users")
    private int totalUsers;
    @OneToOne(mappedBy = "adminAccount")
    Account account;
}
