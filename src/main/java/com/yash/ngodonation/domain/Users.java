package com.yash.ngodonation.domain;

/**
 * Represents a user of the system, storing information such as name, ID,
 * email, phone number, and password.
 */
public class Users {
    /**
     * The user's full name.
     */
    private String name;

    /**
     * The unique identifier for the user.
     */
    private int id;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The user's phone number.
     */
    private String phone;

    /**
     * The user's password for authentication.
     */
    private String password;

    /**
     * Default constructor for the {@code Users} class.
     * Creates a {@code Users} object with default values.
     */
    public Users() {
    }

    /**
     * Constructs a {@code Users} object with the specified attributes.
     *
     * @param name     The user's full name.
     * @param id       The unique identifier for the user.
     * @param phone    The user's phone number.
     * @param email    The user's email address.
     * @param password The user's password.
     */
    public Users(String name, int id, String phone, String email, String password) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the user's full name.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     *
     * @param name The user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id The user's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's email address.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The user's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's phone number.
     *
     * @return The user's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phone The user's phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the {@code Users} object.
     *
     * @return A string containing the user's attributes.
     */
    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}