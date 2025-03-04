package com.yash.ngodonation.daoimplementation;

import com.yash.ngodonation.dao.CampaignDao;
import com.yash.ngodonation.domain.Campaigns;
import com.yash.ngodonation.utility.JdbcUtlity;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampaignDaoImp implements CampaignDao {

    @Override
    public boolean createCampaign(Campaigns campaign) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtlity.getConnection();
            String sql = "INSERT INTO campaigns (title, description, goal_amount, collected_amount, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, campaign.getTitle());
            preparedStatement.setString(2, campaign.getDescription());
            preparedStatement.setBigDecimal(3, campaign.getGoalAmount());
            preparedStatement.setBigDecimal(4, campaign.getCollectedAmount());
            preparedStatement.setDate(5, campaign.getStartDate());
            preparedStatement.setDate(6, campaign.getEndDate());
            preparedStatement.setString(7, campaign.getStatus());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } finally {
            JdbcUtlity.close(preparedStatement);
            JdbcUtlity.close(connection);
        }
    }

    @Override
    public Campaigns getCampaignById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Campaigns campaign = null;

        try {
            connection = JdbcUtlity.getConnection();
            String sql = "SELECT id, title, description, goal_amount, collected_amount, start_date, end_date, status FROM campaigns WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                campaign = new Campaigns();
                campaign.setId(resultSet.getInt("id"));
                campaign.setTitle(resultSet.getString("title"));
                campaign.setDescription(resultSet.getString("description"));
                campaign.setGoalAmount(resultSet.getBigDecimal("goal_amount")); // Corrected
                campaign.setCollectedAmount(resultSet.getBigDecimal("collected_amount")); // Corrected
                campaign.setStartDate(resultSet.getDate("start_date")); // Corrected
                campaign.setEndDate(resultSet.getDate("end_date")); // Corrected
                campaign.setStatus(resultSet.getString("status"));
            }
        } finally {
            JdbcUtlity.close(resultSet);
            JdbcUtlity.close(preparedStatement);
            JdbcUtlity.close(connection);
        }
        return campaign;
    }

    @Override
    public List<Campaigns> getAllCampaigns() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Campaigns> campaigns = new ArrayList<>();

        try {
            connection = JdbcUtlity.getConnection();
            String sql = "SELECT id, title, description, goal_amount, collected_amount, start_date, end_date, status FROM campaigns";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Campaigns campaign = new Campaigns();
                campaign.setId(resultSet.getInt("id"));
                campaign.setTitle(resultSet.getString("title"));
                campaign.setDescription(resultSet.getString("description"));
                campaign.setGoalAmount(resultSet.getBigDecimal("goal_amount")); // Corrected
                campaign.setCollectedAmount(resultSet.getBigDecimal("collected_amount")); // Corrected
                campaign.setStartDate(resultSet.getDate("start_date")); // Corrected
                campaign.setEndDate(resultSet.getDate("end_date")); // Corrected
                campaign.setStatus(resultSet.getString("status"));
                campaigns.add(campaign);
            }
        } finally {
            JdbcUtlity.close(resultSet);
            JdbcUtlity.close(statement);
            JdbcUtlity.close(connection);
        }
        return campaigns;
    }

    @Override
    public boolean updateCampaign(Campaigns campaign) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtlity.getConnection();
            String sql = "UPDATE campaigns SET title = ?, description = ?, goal_amount = ?, collected_amount = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, campaign.getTitle());
            preparedStatement.setString(2, campaign.getDescription());
            preparedStatement.setBigDecimal(3, campaign.getGoalAmount());
            preparedStatement.setBigDecimal(4, campaign.getCollectedAmount());
            preparedStatement.setDate(5, campaign.getStartDate());
            preparedStatement.setDate(6, campaign.getEndDate());
            preparedStatement.setString(7, campaign.getStatus());
            preparedStatement.setInt(8, campaign.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } finally {
            JdbcUtlity.close(preparedStatement);
            JdbcUtlity.close(connection);
        }
    }

    @Override
    public boolean deleteCampaign(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtlity.getConnection();
            String sql = "DELETE FROM campaigns WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } finally {
            JdbcUtlity.close(preparedStatement);
            JdbcUtlity.close(connection);
        }
    }

    @Override
    public boolean updateCollectedAmount(int campaignId, BigDecimal amount) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtlity.getConnection();
            String sql = "UPDATE campaigns SET collected_amount = collected_amount + ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.setInt(2, campaignId);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } finally {
            JdbcUtlity.close(preparedStatement);
            JdbcUtlity.close(connection);
        }
    }
}