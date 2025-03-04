package com.yash.ngodonation.daoimplementation;

import com.yash.ngodonation.dao.RegisterDao;
import com.yash.ngodonation.utility.JdbcUtlity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDaoImp implements RegisterDao {

    @Override
    public boolean registerDonor(String name, String email, String phone, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;

        try {
            connection = JdbcUtlity.getConnection();

            String sql = "INSERT INTO users(name, email, phone, password) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        } finally {
            JdbcUtlity.close(preparedStatement);
            JdbcUtlity.close(connection);
        }
        return success;
    }
}