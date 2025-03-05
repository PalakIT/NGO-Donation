package com.yash.ngodonation.serviceimplementation;
import com.yash.ngodonation.dao.LoginDao;
import com.yash.ngodonation.daoimplementation.LoginDaoImp;
import com.yash.ngodonation.domain.Users;
import com.yash.ngodonation.service.LoginService;

import java.sql.SQLException;

/**
 * Implementation of the {@link LoginService} interface, providing business logic
 * for user authentication.
 */
public class LoginServiceImpl implements LoginService {

    /**
     * The Data Access Object (DAO) used to interact with the database for user login-related operations.
     */
    private final LoginDao loginDao;

    /**
     * Constructs a {@code LoginServiceImpl} with a specific {@link LoginDao}.
     * Used for dependency injection, facilitating testing and alternative data source implementations.
     *
     * @param loginDao The {@link LoginDao} to use for data access.
     */
    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    /**
     * Constructs a {@code LoginServiceImpl} with a default {@link LoginDao} implementation.
     */
    public LoginServiceImpl(){
        this(new LoginDaoImp());
    }

    /**
     * Authenticates a user based on the provided email and password, applying business logic
     * and validation rules.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return The {@link Users} object if authentication is successful, {@code null} otherwise.
     * @throws SQLException           If a database error occurs during the operation.
     * @throws IllegalArgumentException If the provided email or password is invalid.
     */
    @Override
    public Users authenticateUser(String email, String password) throws SQLException {

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty.");
        }

        // Call the DAO to authenticate the user
        Users user = loginDao.authenticateUser(email, password);

        return user;
    }
}