package com.yash.ngodonation.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Utility class for managing JDBC connections, providing methods for getting connections
 * and safely closing resources.
 */
public class JdbcUtlity {

    /**
     * The URL for the MySQL database connection.
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ngo_db";

    /**
     * The username for the database connection.
     */
    private static final String DB_USER = "root";

    /**
     * The password for the database connection.
     */
    private static final String DB_PASSWORD = "root";

    /**
     * Establishes a connection to the database using the provided credentials.
     *
     * @return A {@link Connection} object representing the database connection.
     * @throws SQLException If a database error occurs during the connection process or if the JDBC driver is not found.
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            throw new SQLException("MySQL JDBC Driver not found!", e);
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Closes the given database connection, handling potential exceptions.
     *
     * @param connection The {@link Connection} object to close.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * Closes the given statement, handling potential exceptions.
     *
     * @param statement The {@link Statement} object to close.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
        }
    }

    /**
     * Closes the given result set, handling potential exceptions.
     *
     * @param resultSet The {@link ResultSet} object to close.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println("Error closing result set: " + e.getMessage());
            }
        }
    }
}