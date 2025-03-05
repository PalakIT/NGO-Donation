package com.yash.ngodonation.dao;
import com.yash.ngodonation.controller.Campaign;
import com.yash.ngodonation.domain.Campaigns;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface defining the data access operations for {@link Campaigns} entities.
 * Provides methods for creating, retrieving, updating, and deleting campaign information
 * from the data store.
 */
public interface CampaignDao {

    /**
     * Creates a new campaign in the data store.
     *
     * @param campaign The {@link Campaigns} object containing the data for the new campaign.
     * @return {@code true} if the campaign was successfully created, {@code false} otherwise.
     * @throws SQLException If a database error occurs during the operation.
     */
    boolean createCampaign(Campaigns campaign) throws SQLException;

    /**
     * Retrieves a campaign from the data store based on its ID.
     *
     * @param id The ID of the campaign to retrieve.
     * @return The {@link Campaigns} object corresponding to the given ID, or {@code null} if not found.
     * @throws SQLException If a database error occurs during the operation.
     */
    Campaigns getCampaignById(int id) throws SQLException;

    /**
     * Retrieves all campaigns from the data store.
     *
     * @return A {@link List} of {@link Campaigns} objects representing all campaigns in the data store.
     *         Returns an empty list if no campaigns are found.
     * @throws SQLException If a database error occurs during the operation.
     */
    List<Campaigns> getAllCampaigns() throws SQLException;

    /**
     * Updates an existing campaign in the data store.
     *
     * @param campaign The {@link Campaigns} object containing the updated data.  The ID of the campaign
     *                 to update is taken from this object.
     * @return {@code true} if the campaign was successfully updated, {@code false} otherwise.
     * @throws SQLException If a database error occurs during the operation.
     */
    boolean updateCampaign(Campaigns campaign) throws SQLException;

    /**
     * Deletes a campaign from the data store based on its ID.
     *
     * @param id The ID of the campaign to delete.
     * @return {@code true} if the campaign was successfully deleted, {@code false} otherwise.
     * @throws SQLException If a database error occurs during the operation.
     */
    boolean deleteCampaign(int id) throws SQLException;

    /**
     * Updates the collected amount for a specific campaign.
     *
     * @param campaignId The ID of the campaign to update.
     * @param amount     The amount to *add* to the currently collected amount.
     * @return {@code true} if the collected amount was successfully updated, {@code false} otherwise.
     * @throws SQLException If a database error occurs during the operation.
     */
    boolean updateCollectedAmount(int campaignId, BigDecimal amount) throws SQLException;
}