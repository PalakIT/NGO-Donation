package com.yash.ngodonation.domain;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Represents a campaign for an NGO, containing information about the campaign's goals,
 * progress, and timeline.
 */
public class Campaigns {
    /**
     * The unique identifier for the campaign.
     */
    private int id;

    /**
     * The title of the campaign.
     */
    private String title;

    /**
     * A detailed description of the campaign's purpose and objectives.
     */
    private String description;

    /**
     * The target amount of money to be raised by the campaign.
     */
    private BigDecimal goal_amount;

    /**
     * The total amount of money collected so far for the campaign.
     */
    private BigDecimal collected_amount;

    /**
     * The date on which the campaign officially started.
     */
    private Date start_date;

    /**
     * The date on which the campaign is scheduled to end.
     */
    private Date end_date;

    /**
     * The current status of the campaign (e.g., "Active", "Completed", "Pending").
     */
    private String status;

    /**
     * Default constructor for the {@code Campaigns} class.
     * Creates a {@code Campaigns} object with default values.
     */
    public Campaigns() {}

    /**
     * Constructs a {@code Campaigns} object with the specified attributes.
     *
     * @param id             The unique identifier for the campaign.
     * @param title          The title of the campaign.
     * @param description    A detailed description of the campaign.
     * @param goalAmount     The target amount of money to be raised.
     * @param collectedAmount The total amount of money collected so far.
     * @param startDate      The date on which the campaign started.
     * @param endDate        The date on which the campaign is scheduled to end.
     * @param status         The current status of the campaign.
     */
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

    /**
     * Gets the unique identifier for the campaign.
     *
     * @return The campaign's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the campaign.
     *
     * @param id The campaign's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the campaign.
     *
     * @return The campaign's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the campaign.
     *
     * @param title The campaign's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the campaign.
     *
     * @return The campaign's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the campaign.
     *
     * @param description The campaign's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the target amount of money to be raised by the campaign.
     *
     * @return The campaign's goal amount.
     */
    public BigDecimal getGoalAmount() {
        return goal_amount;
    }

    /**
     * Sets the target amount of money to be raised by the campaign.
     *
     * @param goalAmount The campaign's goal amount.
     */
    public void setGoalAmount(BigDecimal goalAmount) {
        this.goal_amount = goalAmount;
    }

    /**
     * Gets the total amount of money collected so far for the campaign.
     *
     * @return The campaign's collected amount.
     */
    public BigDecimal getCollectedAmount() {
        return collected_amount;
    }

    /**
     * Sets the total amount of money collected so far for the campaign.
     *
     * @param collectedAmount The campaign's collected amount.
     */
    public void setCollectedAmount(BigDecimal collectedAmount) {
        this.collected_amount= collectedAmount;
    }

    /**
     * Gets the date on which the campaign started.
     *
     * @return The campaign's start date.
     */
    public Date getStartDate() {
        return start_date;
    }

    /**
     * Sets the date on which the campaign started.
     *
     * @param startDate The campaign's start date.
     */
    public void setStartDate(Date startDate) {
        this.start_date = startDate;
    }

    /**
     * Gets the date on which the campaign is scheduled to end.
     *
     * @return The campaign's end date.
     */
    public Date getEndDate() {
        return end_date;
    }

    /**
     * Sets the date on which the campaign is scheduled to end.
     *
     * @param endDate The campaign's end date.
     */
    public void setEndDate(Date endDate) {
        this.end_date= endDate;
    }

    /**
     * Gets the current status of the campaign.
     *
     * @return The campaign's status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the campaign.
     *
     * @param status The campaign's status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the {@code Campaigns} object.
     *
     * @return A string containing the campaign's attributes.
     */
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