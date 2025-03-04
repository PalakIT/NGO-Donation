package com.yash.ngodonation.serviceimplementation;

import com.yash.ngodonation.dao.RegisterDao;
import com.yash.ngodonation.daoimplementation.RegisterDaoImp;
import com.yash.ngodonation.service.RegisterService;

import java.sql.SQLException;

public class RegisterServiceImpl implements RegisterService {

    private final RegisterDao registerDao;

    public RegisterServiceImpl(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    // Default constructor in case you want to instantiate with no arguments
    public RegisterServiceImpl() {
        this(new RegisterDaoImp()); // Use the default implementation
    }


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