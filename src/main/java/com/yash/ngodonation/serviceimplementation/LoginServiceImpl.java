package com.yash.ngodonation.serviceimplementation;
import com.yash.ngodonation.dao.LoginDao;
import com.yash.ngodonation.daoimplementation.LoginDaoImp;
import com.yash.ngodonation.domain.Users;
import com.yash.ngodonation.service.LoginService;

import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public LoginServiceImpl(){
        this(new LoginDaoImp());
    }

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