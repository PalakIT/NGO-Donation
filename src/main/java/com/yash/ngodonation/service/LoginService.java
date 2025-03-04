package com.yash.ngodonation.service;

import com.yash.ngodonation.domain.Users;

import java.sql.SQLException;

public interface LoginService {

    Users authenticateUser(String email, String password) throws SQLException;

}