package com.nrw.non_revenue_water.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Credential {
    @Column(unique = true, length = 50, nullable = false)
    private String accountEmail;
    @Column(nullable = false)
    private String accountPassword;
}
