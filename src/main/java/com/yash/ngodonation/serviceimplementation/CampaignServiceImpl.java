package com.yash.ngodonation.serviceimplementation;

import com.yash.ngodonation.dao.CampaignDao;
import com.yash.ngodonation.daoimplementation.CampaignDaoImp;
import com.yash.ngodonation.domain.Campaigns;
import com.yash.ngodonation.service.CampaignService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the {@link CampaignService} interface, providing business logic
 * and validation for managing {@link Campaigns} entities.
 */
public class CampaignServiceImpl implements CampaignService {

    /**
     * The Data Access Object (DAO) used to interact with the database for campaign-related operations.
     */
    private CampaignDao campaignDao = new CampaignDaoImp(); // Dependency on DAO

    /**
     * Creates a new campaign, applying business logic and validation rules.
     *
     * @param campaign The {@link Campaigns} object containing the data for the new campaign.
     * @return {@code true} if the campaign was successfully created, {@code false} otherwise.
     * @throws SQLException           If a database error occurs during the operation.
     * @throws IllegalArgumentException If the provided campaign data is invalid.
     */
    @Override
    public boolean createCampaign(Campaigns campaign) throws SQLException {
        if (!isValidCampaign(campaign)) {
            throw new IllegalArgumentException("Invalid campaign data. Please check the fields.");
        }

        if (campaign.getGoalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Goal amount must be positive.");
        }

        if (campaign.getCollectedAmount() == null) {
            campaign.setCollectedAmount(BigDecimal.ZERO);
        }


        return campaignDao.createCampaign(campaign);
    }

    /**
     * Retrieves a campaign by its ID, applying business logic and validation rules.
     *
     * @param id The ID of the campaign to retrieve.
     * @return The {@link Campaigns} object corresponding to the given ID, or {@code null} if not found.
     * @throws SQLException           If a database error occurs during the operation.
     * @throws IllegalArgumentException If the provided campaign ID is invalid.
     */
    @Override
    public Campaigns getCampaignById(int id) throws SQLException {
        // Business Logic: Check if ID is valid (positive)
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid campaign ID.");
        }

        return campaignDao.getCampaignById(id);
    }

    /**
     * Retrieves all campaigns, applying business logic and validation rules.
     *
     * @return A {@link List} of {@link Campaigns} objects representing all campaigns.
     *         Returns an empty list if no campaigns are found.
     * @throws SQLException If a database error occurs during the operation.
     */
    @Override
    public List<Campaigns> getAllCampaigns() throws SQLException {

        return campaignDao.getAllCampaigns();
    }

    /**
     * Updates an existing campaign, applying business logic and validation rules.
     *
     * @param campaign The {@link Campaigns} object containing the updated data. The ID of the campaign
     *                 to update is taken from this object.
     * @return {@code true} if the campaign was successfully updated, {@code false} otherwise.
     * @throws SQLException           If a database error occurs during the operation.
     * @throws IllegalArgumentException If the provided campaign data is invalid.
     */
    @Override
    public boolean updateCampaign(Campaigns campaign) throws SQLException {
        if (!isValidCampaign(campaign)) {
            throw new IllegalArgumentException("Invalid campaign data. Please check the fields.");
        }

        if (campaign.getGoalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Goal amount must be positive.");
        }

        Campaigns existingCampaign = campaignDao.getCampaignById(campaign.getId());
        if (existingCampaign != null && campaign.getCollectedAmount().compareTo(existingCampaign.getCollectedAmount()) < 0) {
            throw new IllegalArgumentException("Cannot decrease the collected amount.");
        }

        return campaignDao.updateCampaign(campaign);
    }

    /**
     * Deletes a campaign, applying business logic and validation rules.
     *
     * @param id The ID of the campaign to delete.
     * @return {@code true} if the campaign was successfully deleted, {@code false} otherwise.
     * @throws SQLException           If a database error occurs during the operation.
     * @throws IllegalArgumentException If the provided campaign ID is invalid or the campaign cannot be deleted.
     */
    @Override
    public boolean deleteCampaign(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid campaign ID.");
        }

        Campaigns campaign = campaignDao.getCampaignById(id);
        if (campaign != null && "Active".equalsIgnoreCase(campaign.getStatus())) {
            throw new IllegalArgumentException("Cannot delete an active campaign.");
        }

        return campaignDao.deleteCampaign(id);
    }

    /**
     * Updates the collected amount for a specific campaign, applying business logic and validation rules.
     *
     * @param campaignId The ID of the campaign to update.
     * @param amount     The amount to add to the currently collected amount.
     * @return {@code true} if the collected amount was successfully updated, {@code false} otherwise.
     * @throws SQLException           If a database error occurs during the operation.
     * @throws IllegalArgumentException If the provided amount is invalid.
     */
    @Override
    public boolean updateCollectedAmount(int campaignId, BigDecimal amount) throws SQLException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        return campaignDao.updateCollectedAmount(campaignId, amount);
    }

    /**
     * Helper method to validate the essential fields of a {@link Campaigns} object.
     *
     * @param campaign The {@link Campaigns} object to validate.
     * @return {@code true} if the campaign is valid, {@code false} otherwise.
     */
    private boolean isValidCampaign(Campaigns campaign) {
        if (campaign == null) {
            return false;
        }

        if (campaign.getTitle() == null || campaign.getTitle().trim().isEmpty()) {
            return false;
        }

        if (campaign.getDescription() == null || campaign.getDescription().trim().isEmpty()) {
            return false;
        }

        if (campaign.getStartDate() == null || campaign.getEndDate() == null) {
            return false;
        }

        if (campaign.getStartDate().after(campaign.getEndDate())) {
            return false;
        }

        if (campaign.getStatus() == null || campaign.getStatus().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}