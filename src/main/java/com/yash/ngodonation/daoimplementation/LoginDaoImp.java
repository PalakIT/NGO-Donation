package com.yash.ngodonation.daoimplementation;

import com.yash.ngodonation.dao.LoginDao;
import com.yash.ngodonation.domain.Users;
import com.yash.ngodonation.utility.JdbcUtlity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of the {@link LoginDao} interface, providing data access operations
 * for user authentication using JDBC.
 */
public class LoginDaoImp implements LoginDao {

    /**
     * Authenticates a user based on the provided email and password by querying both the
     * 'admin' and 'users' tables.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return The {@link Users} object if authentication is successful (found in either table),
     *         {@code null} otherwise.
     * @throws SQLException If a database error occurs during the operation.
     */
    @Override
    public Users authenticateUser(String email, String password) throws SQLException {
        Users user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtlity.getConnection();
            // Try to find the user in the admin table to find the userId
            String adminSql = "SELECT id, email FROM admin WHERE email = ? AND password = ?";
            preparedStatement = connection.prepareStatement(adminSql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
            } else {
                // Updated SQL to reflect current table structure
                String userSql = "SELECT id, name, email, password, phone FROM users WHERE email = ? AND password = ?";
                preparedStatement = connection.prepareStatement(userSql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = new Users();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPhone(resultSet.getString("phone"));

                }
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            throw e; // Rethrow for Servlet to handle
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
            JdbcUtlity.close(connection);
        }
        return user;
    }
}