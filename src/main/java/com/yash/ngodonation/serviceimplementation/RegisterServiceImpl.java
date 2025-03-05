package com.yash.ngodonation.serviceimplementation;

import com.yash.ngodonation.dao.RegisterDao;
import com.yash.ngodonation.daoimplementation.RegisterDaoImp;
import com.yash.ngodonation.service.RegisterService;

import java.sql.SQLException;

/**
 * Implementation of the {@link RegisterService} interface, providing business logic
 * and validation for registering new donors.
 */
public class RegisterServiceImpl implements RegisterService {

    /**
     * The Data Access Object (DAO) used to interact with the database for user registration-related operations.
     */
    private final RegisterDao registerDao;

    /**
     * Constructs a {@code RegisterServiceImpl} with a specific {@link RegisterDao}.
     * Used for dependency injection, facilitating testing and alternative data source implementations.
     *
     * @param registerDao The {@link RegisterDao} to use for data access.
     */
    public RegisterServiceImpl(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    /**
     * Constructs a {@code RegisterServiceImpl} with a default {@link RegisterDao} implementation.
     */
    public RegisterServiceImpl() {
        this(new RegisterDaoImp()); // Use the default implementation
    }

    /**
     * Registers a new donor in the system, applying business logic and validation rules.
     *
     * @param name     The name of the donor.
     * @param email    The email address of the donor.
     * @param phone    The phone number of the donor.
     * @param password The password for the donor's account.
     * @return {@code true} if the donor was successfully registered, {@code false} otherwise.
     * @throws SQLException           If a database error occurs during the operation.
     * @throws IllegalArgumentException If the provided registration data is invalid.
     */
    @Override
    public boolean registerDonor(String name, String email, String phone, String password) throws SQLException {
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("All fields are required for registration."); // Or return false, depending on your design
        }

        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email format."); // Or return false
        }
        return registerDao.registerDonor(name, email, phone, password);
    }
}