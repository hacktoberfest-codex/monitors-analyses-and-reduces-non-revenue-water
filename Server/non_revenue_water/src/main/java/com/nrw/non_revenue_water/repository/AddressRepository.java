package com.nrw.non_revenue_water.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nrw.non_revenue_water.model.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

}
