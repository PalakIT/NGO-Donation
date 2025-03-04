package com.yash.ngodonation.dao;

import java.sql.SQLException;

public interface RegisterDao {

    boolean registerDonor(String name, String email, String phone, String password) throws SQLException;
}