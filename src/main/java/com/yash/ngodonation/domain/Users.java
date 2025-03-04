package com.yash.ngodonation.domain;

public class Users {
    private String name;
    private int id;
    private String email;
    private String phone;  // Changed to String to match the database
    //private String location;  // Removed location as it's not in the current database
    private String password;

    //default constructor
    public Users() {
    }

    //parametrized constructor
    public Users(String name, int id, String phone, String email, String password) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    //getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {  // Changed return type to String
        return phone;
    }

    public void setPhone(String phone) {  // Changed parameter type to String
        this.phone = phone;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //To String

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +  // Changed to String
                ", password='" + password + '\'' +
                '}';
    }
}