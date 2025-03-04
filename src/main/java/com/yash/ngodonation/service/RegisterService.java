package com.yash.ngodonation.service;

import java.sql.SQLException;

public interface RegisterService {

    boolean registerDonor(String name, String email, String phone, String password) throws SQLException;

}