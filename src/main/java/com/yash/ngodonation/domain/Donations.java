package com.yash.ngodonation.domain;

import java.sql.Timestamp;
import java.math.BigDecimal;  // Import BigDecimal

public class Donations {
    private int id;
    private int donor_id;
    private BigDecimal amount; // Changed to BigDecimal
    private String status;
    private Timestamp date;


    public Donations() {
    }

    public Donations(int id, int donor_id, BigDecimal amount, String status, Timestamp date) {
        this.id = id;
        this.donor_id = donor_id;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


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