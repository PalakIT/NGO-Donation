package com.yash.ngodonation.controller;

import com.yash.ngodonation.dao.CampaignDao;
import com.yash.ngodonation.daoimplementation.CampaignDaoImp;
import com.yash.ngodonation.domain.Campaigns;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/campaign")
public class Campaign extends HttpServlet {

    private CampaignDao campaignDao = new CampaignDaoImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try { // Add try-catch for overall error handling
            if ("list".equals(action)) {
                listCampaigns(request, response);
            } else if ("get".equals(action)) {
                getCampaign(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action: " + action);
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.  See console for details.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    createCampaign(request, response);
                    break;
                case "update":
                    updateCampaign(request, response);
                    break;
                case "delete":
                    deleteCampaign(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action: " + action);
                    break;
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.  See console for details.");
        }
    }

    private void listCampaigns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Campaigns> campaigns = campaignDao.getAllCampaigns();
            System.out.println(campaigns);

            System.out.println("Number of campaigns retrieved: " + campaigns.size());

            request.setAttribute("campaigns", campaigns);
            request.getRequestDispatcher("campaign.jsp").forward(request, response);

        } catch (SQLException e) {
            System.err.println("Error fetching campaigns from database: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching campaigns: " + e.getMessage());
        } catch (ServletException | IOException e) {
            System.err.println("Error forwarding to JSP: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error forwarding to JSP: " + e.getMessage());
        }
    }

    private void getCampaign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Campaigns campaign = campaignDao.getCampaignById(id);

            if (campaign != null) {
                request.setAttribute("campaign", campaign);
                request.getRequestDispatcher("campaignDetails.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Campaign not found");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching campaign by ID: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching campaign: " + e.getMessage());
        } catch (ServletException | IOException e) {
            System.err.println("Error forwarding to JSP: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error forwarding to JSP: " + e.getMessage());
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid campaign ID.");
        }
    }

    private void createCampaign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String goalAmountStr = request.getParameter("goalAmount"); // Get as String first
            String collectedAmountStr = request.getParameter("collectedAmount"); // New Line, get as String
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String status = request.getParameter("status");

            //VALIDATION
            if (title == null || title.trim().isEmpty() || description == null || description.trim().isEmpty() ||
                    goalAmountStr == null || goalAmountStr.trim().isEmpty() || collectedAmountStr == null || collectedAmountStr.trim().isEmpty() ||  startDateStr == null || startDateStr.trim().isEmpty() ||
                    endDateStr == null || endDateStr.trim().isEmpty() || status == null || status.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
                return;
            }

            BigDecimal goalAmount;
            BigDecimal collectedAmount;
            Date startDate;
            Date endDate;

            try {
                goalAmount = new BigDecimal(goalAmountStr);
                collectedAmount = new BigDecimal(collectedAmountStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format for goal or collected amount.");
                return;
            }

            try {
                startDate = Date.valueOf(startDateStr);
                endDate = Date.valueOf(endDateStr);
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format.  Use YYYY-MM-DD.");
                return;
            }

            if (startDate.after(endDate)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Start date must be before end date.");
                return;
            }

            Campaigns newCampaign = new Campaigns();
            newCampaign.setTitle(title);
            newCampaign.setDescription(description);
            newCampaign.setGoalAmount(goalAmount);
            newCampaign.setCollectedAmount(collectedAmount);
            newCampaign.setStartDate(startDate);
            newCampaign.setEndDate(endDate);
            newCampaign.setStatus(status);

            boolean created = campaignDao.createCampaign(newCampaign);

            if (created) {
                response.sendRedirect("campaign?action=list");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create campaign.");
            }
        } catch (SQLException e) {
            System.err.println("Error creating campaign in database: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating campaign: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid argument: " + e.getMessage());
        }
    }

    private void updateCampaign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String campaignIdStr = request.getParameter("campaignId"); // Get campaignId as String
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String goalAmountStr = request.getParameter("goalAmount"); // Get as String first
            String collectedAmountStr = request.getParameter("collectedAmount"); // New Line, get as String
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String status = request.getParameter("status");

            // **VALIDATION:**  Crucial input validation
            if (campaignIdStr == null || campaignIdStr.trim().isEmpty() || title == null || title.trim().isEmpty() || description == null || description.trim().isEmpty() ||
                    goalAmountStr == null || goalAmountStr.trim().isEmpty() || collectedAmountStr == null || collectedAmountStr.trim().isEmpty() ||  startDateStr == null || startDateStr.trim().isEmpty() ||
                    endDateStr == null || endDateStr.trim().isEmpty() || status == null || status.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
                return; // Stop processing
            }
            int id;
            try {
                id = Integer.parseInt(campaignIdStr); //Then Parse
            } catch(NumberFormatException e){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Campaign ID.");
                return;
            }


            BigDecimal goalAmount;
            BigDecimal collectedAmount;
            Date startDate;
            Date endDate;

            try {
                goalAmount = new BigDecimal(goalAmountStr);
                collectedAmount = new BigDecimal(collectedAmountStr); // New Line
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format for goal or collected amount.");
                return;
            }

            try {
                startDate = Date.valueOf(startDateStr);
                endDate = Date.valueOf(endDateStr);
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format.  Use YYYY-MM-DD.");
                return;
            }

            if (startDate.after(endDate)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Start date must be before end date.");
                return;
            }


            Campaigns campaignToUpdate = new Campaigns();
            campaignToUpdate.setId(id);
            campaignToUpdate.setTitle(title);
            campaignToUpdate.setDescription(description);
            campaignToUpdate.setGoalAmount(goalAmount);
            campaignToUpdate.setCollectedAmount(collectedAmount); // new line
            campaignToUpdate.setStartDate(startDate);
            campaignToUpdate.setEndDate(endDate);
            campaignToUpdate.setStatus(status);

            boolean updated = campaignDao.updateCampaign(campaignToUpdate);

            if (updated) {
                response.sendRedirect("campaign?action=list");  // Redirect to list after update
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Campaign not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating campaign in database: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating campaign: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid argument: " + e.getMessage());
        }
    }

    private void deleteCampaign(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String campaignIdStr = request.getParameter("campaignId");

            if (campaignIdStr == null || campaignIdStr.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Campaign ID is required.");
                return;
            }

            int id = Integer.parseInt(campaignIdStr);
            boolean deleted = campaignDao.deleteCampaign(id);

            if (deleted) {
                response.sendRedirect("campaign?action=list");  // Redirect to list after delete

            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Campaign not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting campaign from database: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting campaign: " + e.getMessage());
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid campaign ID.");
        }
    }
}