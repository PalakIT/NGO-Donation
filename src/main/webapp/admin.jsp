<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yash.ngodonation.domain.Campaigns" %>
<%@ page import="com.yash.ngodonation.daoimplementation.CampaignDaoImp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.math.BigDecimal" %>

<%
    if(session.getAttribute("id") == null){
    response.sendRedirect("login.jsp");
    return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Care Foundation Admin Dashboard</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="admin.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
</head>
<body>
<%
    // Initialize DAO
    CampaignDaoImp campaignDao = new CampaignDaoImp();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Handle form submissions
    String action = request.getParameter("action");
    if ("create".equals(action) || "update".equals(action)) {
        try {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            BigDecimal goalAmount = new BigDecimal(request.getParameter("goalAmount"));
            BigDecimal collectedAmount = new BigDecimal(request.getParameter("collectedAmount"));
            java.util.Date startDate = dateFormat.parse(request.getParameter("startDate"));
            java.util.Date endDate = dateFormat.parse(request.getParameter("endDate"));
            String status = request.getParameter("status");

            Campaigns campaign = new Campaigns();
            campaign.setTitle(title);
            campaign.setDescription(description);
            campaign.setGoalAmount(goalAmount);
            campaign.setCollectedAmount(collectedAmount);
            campaign.setStartDate(new java.sql.Date(startDate.getTime()));
            campaign.setEndDate(new java.sql.Date(endDate.getTime()));
            campaign.setStatus(status);

            if ("create".equals(action)) {
                campaignDao.createCampaign(campaign);
            } else {
                int campaignId = Integer.parseInt(request.getParameter("campaignId"));
                campaign.setId(campaignId);
                campaignDao.updateCampaign(campaign);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if ("delete".equals(action)) {
        try {
            int campaignId = Integer.parseInt(request.getParameter("campaignId"));
            campaignDao.deleteCampaign(campaignId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String campaignsSectionDisplay = "none";
    String donationsSectionDisplay = "none";

    if(request.getParameter("showSection")!=null) {
        if(request.getParameter("showSection").equals("campaigns")) {
            campaignsSectionDisplay = "block";
        } else if(request.getParameter("showSection").equals("donations")) {
            donationsSectionDisplay = "block";
        }
    }

    String campaignFormDisplay = request.getParameter("campaignFormDisplay") != null ? "block" : "none";

    // Fetch campaigns
    List<Campaigns> campaigns = null;
    try {
        campaigns = campaignDao.getAllCampaigns();
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Count active campaigns
    int activeCampaigns = 0;
    int completedCampaigns = 0;
    BigDecimal totalCollected = BigDecimal.ZERO;

    if (campaigns != null) {
        for(Campaigns campaign : campaigns) {
            if("active".equals(campaign.getStatus())) {
                activeCampaigns++;
            } else if("completed".equals(campaign.getStatus())) {
                completedCampaigns++;
            }
            totalCollected = totalCollected.add(campaign.getCollectedAmount());
        }
    }
%>

<!-- Modern Navbar -->
<nav class="navbar">
    <div class="logo">
        Care<span>Foundation</span>
    </div>
    <div class="nav-links">
        <form action="LogoutServlet" method="post">
            <button type="submit" class="login-btn">
                <i class="fas fa-sign-out-alt"></i> Logout
            </button>
        </form>
    </div>
</nav>

<div class="dashboard">
    <!-- Sidebar with Icons -->
    <div class="sidebar">
        <ul class="nav-menu">
            <li class="nav-item">
                <a href="admin.jsp" class="nav-link <%= (request.getParameter("showSection") == null) ? "active" : "" %>">
                    <i class="fas fa-tachometer-alt"></i>
                    Dashboard
                </a>
            </li>
            <li class="nav-item">
                <a href="?showSection=donations" class="nav-link <%= donationsSectionDisplay.equals("block") ? "active" : "" %>">
                    <i class="fas fa-hand-holding-usd"></i>
                    Donations
                </a>
            </li>
            <li class="nav-item">
                <a href="?showSection=campaigns" class="nav-link <%= campaignsSectionDisplay.equals("block") ? "active" : "" %>">
                    <i class="fas fa-bullhorn"></i>
                    Campaigns
                </a>
            </li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="main">
        <!-- Enhanced Stats Cards -->
        <div class="stats-grid">
            <a href="?showSection=donations" class="stat-card" style="text-decoration: none; color: inherit;">
                <h3>Total Donations</h3>
                <div class="stat-value">₹ <%= String.format("%,.2f", totalCollected) %></div>
                <div class="mt-2"><i class="fas fa-arrow-right"></i> View details</div>
            </a>
            <a class="stat-card" style="text-decoration: none; color: inherit;">
                <h3>Active Donors</h3>
                <div class="stat-value">256</div>
                <div class="mt-2"><i class="fas fa-arrow-right"></i> View donors</div>
            </a>
            <a href="?showSection=campaigns&activeOnly=true" class="stat-card" style="text-decoration: none; color: inherit;">
                <h3>Active Campaigns</h3>
                <div class="stat-value"><%= activeCampaigns %></div>
                <div class="mt-2"><i class="fas fa-arrow-right"></i> View active campaigns</div>
            </a>
            <a href="?showSection=campaigns&completedOnly=true" class="stat-card" style="text-decoration: none; color: inherit;">
                <h3>Completed Campaigns</h3>
                <div class="stat-value"><%= completedCampaigns %></div>
                <div class="mt-2"><i class="fas fa-arrow-right"></i> View completed campaigns</div>
            </a>
        </div>

        <!-- Donations Section -->
        <div class="section fade-in" id="donationsSection" style="display:<%= donationsSectionDisplay %>;">
            <div class="section-title">
                <i class="fas fa-hand-holding-usd"></i> Donation Management
            </div>
        </div>

        <!-- Campaign Section -->
        <div class="section fade-in" id="campaignsSection" style="display:<%= campaignsSectionDisplay %>;">
            <div class="section-title">
                <i class="fas fa-bullhorn"></i> Campaign Management
            </div>

            <!-- Create Button -->
            <button class="btn" onclick="document.getElementById('campaignForm').style.display='block'">
                <i class="fas fa-plus"></i> Create New Campaign
            </button>

            <!-- Enhanced Campaign Form -->
            <div id="campaignForm" class="form-container hidden" style="display: <%= campaignFormDisplay %>">
                <h3 class="form-title">
                    <%= (request.getParameter("editCampaignId") != null) ? "Edit Campaign" : "Create New Campaign" %>
                </h3>
                <form action="admin.jsp" method="post" id="campaignFormInner">
                    <input type="hidden" name="action" value="<%= (request.getParameter("editCampaignId") != null) ? "update" : "create" %>"/>
                    <input type="hidden" name="showSection" value="campaigns"/>
                    <div class="form-grid">
                        <div class="form-group">
                            <label for="campaignId">Campaign ID:</label>
                            <input type="number" id="campaignId" name="campaignId" class="form-control"
                                value="<%= (request.getParameter("editCampaignId") != null) ? request.getParameter("editCampaignId") : "" %>"
                                readonly <%= (request.getParameter("editCampaignId") == null) ? "placeholder='Auto-generated'" : "" %>>
                        </div>
                        <div class="form-group">
                            <label for="title">Campaign Title:</label>
                            <input type="text" id="title" name="title" class="form-control"
                                value="<%= (request.getParameter("titleEdit") != null) ? request.getParameter("titleEdit") : "" %>"
                                required placeholder="Enter campaign title">
                        </div>
                        <div class="form-group" style="grid-column: 1 / -1;">
                            <label for="description">Description:</label>
                            <textarea id="description" name="description" class="form-control" rows="4" required
                                placeholder="Provide detailed campaign description"><%= (request.getParameter("descriptionEdit") != null) ? request.getParameter("descriptionEdit") : "" %></textarea>
                        </div>
                        <div class="form-group">
                            <label for="goalAmount">Goal Amount (₹):</label>
                            <input type="number" id="goalAmount" name="goalAmount" step="0.01" class="form-control"
                                value="<%= (request.getParameter("goalAmountEdit") != null) ? request.getParameter("goalAmountEdit") : "" %>"
                                required placeholder="Enter target amount">
                        </div>
                        <div class="form-group">
                            <label for="collectedAmount">Collected Amount (₹):</label>
                            <input type="number" id="collectedAmount" name="collectedAmount" step="0.01" class="form-control"
                                value="<%= (request.getParameter("collectedAmountEdit") != null) ? request.getParameter("collectedAmountEdit") : "0" %>"
                                required placeholder="Enter current amount">
                        </div>
                        <div class="form-group">
                            <label for="startDate">Start Date:</label>
                            <input type="date" id="startDate" name="startDate" class="form-control"
                                value="<%= (request.getParameter("startDateEdit") != null) ? request.getParameter("startDateEdit") : "" %>"
                                required>
                        </div>
                        <div class="form-group">
                            <label for="endDate">End Date:</label>
                            <input type="date" id="endDate" name="endDate" class="form-control"
                                value="<%= (request.getParameter("endDateEdit") != null) ? request.getParameter("endDateEdit") : "" %>"
                                required>
                        </div>
                        <div class="form-group">
                            <label for="status">Status:</label>
                            <select id="status" name="status" class="form-control">
                                <option value="active" <%= "active".equals(request.getParameter("statusEdit")) ? "selected" : "" %>>Active</option>
                                <option value="completed" <%= "completed".equals(request.getParameter("statusEdit")) ? "selected" : "" %>>Completed</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button type="button" class="btn btn-warning" onclick="document.getElementById('campaignForm').style.display='none'">
                            <i class="fas fa-times"></i> Cancel
                        </button>
                        <button type="submit" class="btn btn-success">
                            <i class="fas fa-save"></i> <%= (request.getParameter("editCampaignId") != null) ? "Update" : "Create" %> Campaign
                        </button>
                    </div>
                </form>
            </div>

            <!-- Enhanced Campaign Listing -->
            <div class="table-container mt-4">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Campaign Title</th>
                            <th>Progress</th>
                            <th>Timeline</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        if (campaigns != null && !campaigns.isEmpty()) {
                            for (Campaigns campaign : campaigns) {
                                // Filter by status if requested
                                if (("true".equals(request.getParameter("activeOnly")) && !"active".equals(campaign.getStatus())) ||
                                    ("true".equals(request.getParameter("completedOnly")) && !"completed".equals(campaign.getStatus()))) {
                                    continue;
                                }

                                // Calculate progress percentage
                                BigDecimal goalAmount = campaign.getGoalAmount();
                                BigDecimal collectedAmount = campaign.getCollectedAmount();
                                int progressPercentage = 0;
                                if (goalAmount.compareTo(BigDecimal.ZERO) > 0) {
                                    progressPercentage = collectedAmount.multiply(new BigDecimal(100)).divide(goalAmount, 0, BigDecimal.ROUND_HALF_UP).intValue();
                                }
                        %>
                        <tr>
                            <td><%= campaign.getId() %></td>
                            <td>
                                <strong><%= campaign.getTitle() %></strong>
                                <div class="text-muted mt-1" style="font-size: 12px;">Goal: ₹ <%= String.format("%,.2f", campaign.getGoalAmount()) %></div>
                            </td>
                            <td>
                                <div>₹ <%= String.format("%,.2f", campaign.getCollectedAmount()) %> raised</div>
                                <div class="progress-container">
                                    <div class="progress-bar" style="width: <%= progressPercentage %>%;"></div>
                                </div>
                                <div style="font-size: 12px;"><%= progressPercentage %>% of goal</div>
                            </td>
                            <td>
                                <div><i class="fas fa-calendar-day"></i> <%= dateFormat.format(campaign.getStartDate()) %></div>
                                <div><i class="fas fa-calendar-check"></i> <%= dateFormat.format(campaign.getEndDate()) %></div>
                            </td>
                            <td>
                                <span class="badge <%= "active".equals(campaign.getStatus()) ? "badge-success" : "badge-warning" %>">
                                    <%= "active".equals(campaign.getStatus()) ? "Active" : "Completed" %>
                                </span>
                            </td>
                            <td class="actions">
                                <a href="?showSection=campaigns&campaignFormDisplay=true&editCampaignId=<%= campaign.getId() %>&titleEdit=<%= campaign.getTitle() %>&descriptionEdit=<%= campaign.getDescription() %>&goalAmountEdit=<%= campaign.getGoalAmount() %>&collectedAmountEdit=<%= campaign.getCollectedAmount() %>&startDateEdit=<%= dateFormat.format(campaign.getStartDate()) %>&endDateEdit=<%= dateFormat.format(campaign.getEndDate()) %>&statusEdit=<%= campaign.getStatus() %>" class="action-btn edit-btn">
                                    <i class="fas fa-edit"></i>
                                </a>
                                <a href="?showSection=campaigns&action=delete&campaignId=<%= campaign.getId() %>" class="action-btn delete-btn" onclick="return confirm('Are you sure you want to delete this campaign?');">
                                    <i class="fas fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="6" style="text-align: center;">No campaigns found</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Add animation classes for page elements
        setTimeout(function() {
            document.querySelectorAll('.stats-grid .stat-card').forEach(function(card, index) {
                setTimeout(function() {
                    card.classList.add('animate');
                }, index * 100);
            });
        }, 200);

        // Form validation for date inputs
        const startDateInput = document.getElementById('startDate');
        const endDateInput = document.getElementById('endDate');

        if(startDateInput && endDateInput) {
            endDateInput.addEventListener('change', function() {
                if(startDateInput.value && new Date(endDateInput.value) < new Date(startDateInput.value)) {
                    alert('End date cannot be earlier than start date');
                    endDateInput.value = '';
                }
            });
        }
    });
</script>
</body>
</html>