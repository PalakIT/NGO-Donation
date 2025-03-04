package com.yash.ngodonation.dao;
import com.yash.ngodonation.controller.Campaign;
import com.yash.ngodonation.domain.Campaigns;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface CampaignDao {

    boolean createCampaign(Campaigns campaign) throws SQLException;

    Campaigns getCampaignById(int id) throws SQLException;
    List<Campaigns> getAllCampaigns() throws SQLException;
    boolean updateCampaign(Campaigns campaign) throws SQLException;
    boolean deleteCampaign(int id) throws SQLException;
    boolean updateCollectedAmount(int campaignId, BigDecimal amount) throws SQLException;
}