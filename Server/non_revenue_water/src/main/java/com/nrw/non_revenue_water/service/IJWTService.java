package com.nrw.non_revenue_water.service;

public interface IJWTService {
    String generateToken(String accountNumber);

    /**
     * Return the subject
     */
    String validateToken(String token);
}
