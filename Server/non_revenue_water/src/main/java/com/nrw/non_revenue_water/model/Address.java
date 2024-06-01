package com.nrw.non_revenue_water.model;

import com.nrw.non_revenue_water.model.helper.Auditing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
// meaning of the above the addressId, city.... fields will have private access
// level.
public class Address extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String addressId;
    String city;
    String state;
    String area;
    String zipcode;
    String country;
    @OneToOne(mappedBy = "address")
    Account account;

}
