package com.yash.ngodonation.domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Campaigns {
    private int id;
    private String title;
    private String description;
    private BigDecimal goal_amount;
    private BigDecimal collected_amount;
    private Date start_date;
    private Date end_date;
    private String status;

    public Campaigns() {}

    public Campaigns(int id, String title, String description, BigDecimal goalAmount, BigDecimal collectedAmount, Date startDate, Date endDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.goal_amount = goalAmount;
        this.collected_amount= collectedAmount;
        this.start_date = startDate;
        this.end_date= endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getGoalAmount() {
        return goal_amount;
    }

    public void setGoalAmount(BigDecimal goalAmount) {
        this.goal_amount = goalAmount;
    }

    public BigDecimal getCollectedAmount() {
        return collected_amount;
    }

    public void setCollectedAmount(BigDecimal collectedAmount) {
        this.collected_amount= collectedAmount;
    }

    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date startDate) {
        this.start_date = startDate;
    }

    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date endDate) {
        this.end_date= endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Campaigns{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", goalAmount=" + goal_amount +
                ", collectedAmount=" + collected_amount +
                ", startDate=" + start_date +
                ", endDate=" + end_date +
                ", status='" + status + '\'' +
                '}';
    }
}