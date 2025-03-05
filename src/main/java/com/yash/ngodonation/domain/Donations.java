package com.yash.ngodonation.domain;

import java.sql.Timestamp;
import java.math.BigDecimal;  // Import BigDecimal

/**
 * Represents a donation made by a donor, containing information about the donation amount,
 * status, and timestamp.
 */
public class Donations {
    /**
     * The unique identifier for the donation.
     */
    private int id;

    /**
     * The ID of the donor who made the donation.
     */
    private int donor_id;

    /**
     * The amount of the donation.
     */
    private BigDecimal amount; // Changed to BigDecimal

    /**
     * The status of the donation (e.g., "Completed", "Pending", "Refunded").
     */
    private String status;

    /**
     * The date and time when the donation was made.
     */
    private Timestamp date;


    /**
     * Default constructor for the {@code Donations} class.
     * Creates a {@code Donations} object with default values.
     */
    public Donations() {
    }

    /**
     * Constructs a {@code Donations} object with the specified attributes.
     *
     * @param id       The unique identifier for the donation.
     * @param donor_id The ID of the donor who made the donation.
     * @param amount   The amount of the donation.
     * @param status   The status of the donation.
     * @param date     The date and time when the donation was made.
     */
    public Donations(int id, int donor_id, BigDecimal amount, String status, Timestamp date) {
        this.id = id;
        this.donor_id = donor_id;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }

    /**
     * Gets the unique identifier for the donation.
     *
     * @return The donation's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the donation.
     *
     * @param id The donation's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the donor who made the donation.
     *
     * @return The donor's ID.
     */
    public int getDonor_id() {
        return donor_id;
    }

    /**
     * Sets the ID of the donor who made the donation.
     *
     * @param donor_id The donor's ID.
     */
    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }

    /**
     * Gets the amount of the donation.
     *
     * @return The donation amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the donation.
     *
     * @param amount The donation amount.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the status of the donation.
     *
     * @return The donation status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the donation.
     *
     * @param status The donation status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the date and time when the donation was made.
     *
     * @return The donation timestamp.
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Sets the date and time when the donation was made.
     *
     * @param date The donation timestamp.
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Returns a string representation of the {@code Donations} object.
     *
     * @return A string containing the donation's attributes.
     */
    @Override
    public String toString() {
        return "Donations{" +
                "id=" + id +
                ", donor_id=" + donor_id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}