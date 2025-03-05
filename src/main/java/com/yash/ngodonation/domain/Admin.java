package com.yash.ngodonation.domain;

/**
 * Represents an administrator user within the system.
 * Contains information about the administrator's ID, email, and password.
 */
public class Admin {
    /**
     * The unique identifier for the administrator.
     */
    private int id;

    /**
     * The email address of the administrator.
     */
    private String email;

    /**
     * The password associated with the administrator's account.
     */
    private String password;


    /**
     * Default constructor for the {@code Admin} class.
     * Creates an {@code Admin} object with default values.
     */
    public Admin() {}

    /**
     * Constructs an {@code Admin} object with the specified ID, email, and password.
     *
     * @param id       The unique identifier for the administrator.
     * @param email    The email address of the administrator.
     * @param password The password associated with the administrator's account.
     */
    public Admin(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the unique identifier for the administrator.
     *
     * @return The administrator's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the administrator.
     *
     * @param id The administrator's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the email address of the administrator.
     *
     * @return The administrator's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the administrator.
     *
     * @param email The administrator's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password associated with the administrator's account.
     *
     * @return The administrator's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password associated with the administrator's account.
     *
     * @param password The administrator's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the {@code Admin} object.
     *
     * @return A string containing the administrator's ID, email, and password.
     */
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}