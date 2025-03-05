package com.yash.ngodonation.service;

import java.sql.SQLException;

/**
 * Interface defining the service layer operations for user registration.
 * This layer can contain business logic and validation related to registering a new user.
 */
public interface RegisterService {

    /**
     * Registers a new donor in the system.
     *
     * @param name     The name of the donor.
     * @param email    The email address of the donor.
     * @param phone    The phone number of the donor.
     * @param password The password for the donor's account.
     * @return {@code true} if the donor was successfully registered, {@code false} otherwise.
     * @throws SQLException If a database error occurs during the operation.
     */
    boolean registerDonor(String name, String email, String phone, String password) throws SQLException;

}