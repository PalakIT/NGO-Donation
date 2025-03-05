package com.yash.ngodonation.dao;

import com.yash.ngodonation.domain.Users;

import java.sql.SQLException;

/**
 * Interface defining the data access operations for user authentication.
 */
public interface LoginDao {
     /**
      * Authenticates a user based on the provided email and password.
      *
      * @param email    The email address of the user.
      * @param password The password of the user.
      * @return The {@link Users} object if the authentication is successful, {@code null} otherwise.
      * @throws SQLException If a database error occurs during the operation.
      */
     Users authenticateUser(String email, String password) throws SQLException;
}