package com.yash.ngodonation.dao;

import com.yash.ngodonation.domain.Users;

import java.sql.SQLException;

public interface LoginDao {
     Users authenticateUser(String email, String password) throws SQLException;
}
