<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yash.ngodonation.domain.Campaigns" %>
<%@ page import="com.yash.ngodonation.daoimplementation.CampaignDaoImp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.math.BigDecimal" %>

<%
    if(session.getAttribute("email") == null){
    response.sendRedirect("login.jsp");
    return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NGO Admin Dashboard</title>
     <link rel="stylesheet" href="style.css">
      <link rel="stylesheet" href="admin.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

</head>
<body>
<nav class="navbar">
          <div class="logo">Care<span>Foundation</span></div>
          <div class="nav-links">
              <a href="home.jsp">Home</a>
              <a href="about.jsp">About Us</a>
              <a href="contact.jsp" >Contact</a>
              <a class="active">Campaigns</a>
              <a href="donate.jsp" class="donate-btn">Donate Now</a>
                <form action = "LogoutServlet" method = "post">
                            <button type="submit" class="login-btn">Logout</button>
                            </form>
          </div>
          <div class="hamburger">
              <span></span>
              <span></span>
              <span></span>
          </div>
      </nav>
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
    if(request.getParameter("showSection")!=null && request.getParameter("showSection").equals("campaigns")){
        campaignsSectionDisplay ="block";
    }

    String campaignFormDisplay = request.getParameter("campaignFormDisplay") != null ? "block" : "none";

    // Fetch campaigns
    List<Campaigns> campaigns = null;
    try {
        campaigns = campaignDao.getAllCampaigns();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<div class="dashboard">
    <!-- Sidebar -->
    <div class="sidebar">
        <ul class="nav-menu">
            <li class="nav-item">
                <a href="#" class="nav-link">
                    <i class="fas fa-home"></i>
                    Dashboard
                </a>
            </li>

            <li class="nav-item">
                <a href="#" class="nav-link">
                    <i class="fas fa-hand-holding-heart"></i>
                    Donations
                </a>
            </li>
            <li class="nav-item">
                <a href="?showSection=campaigns" class="nav-link">
                    <i class="fas fa-bullhorn"></i>
                    Campaigns
                </a>
            </li>

            <li class="nav-item">
                <a href="#" class="nav-link">
                    <i class="fas fa-cog"></i>
                    Settings
                </a>
            </li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="main">
        <div class="header">
            <h1>Dashboard Overview</h1>
        </div>

        <!-- Stats Cards -->
        <div class="stats-grid">
            <div class="stat-card">
                <h3>Total Donations</h3>
                <div class="stat-value">8,45,000</div>
                <p>+15% this month</p>
            </div>
            <div class="stat-card">
                <h3>Active Donors</h3>
                <div class="stat-value">256</div>
                <p>+12 new donors</p>
            </div>
            <div class="stat-card">
                <h3>Active Campaigns</h3>
                <div class="stat-value">8</div>
                <p>3 ending soon</p>
            </div>
            <div class="stat-card">
                <h3>Pending Verifications</h3>
                <div class="stat-value">15</div>
                <p>5 new requests</p>
            </div>
        </div>

        <!-- Campaign Section -->
        <div class="section" id="campaignsSection" style="display:<%=campaignsSectionDisplay%>;">
            <h2 class="section-title">Campaign Management</h2>
            <button class="btn" onclick="document.getElementById('campaignForm').style.display='block'">Create New Campaign</button>

            <!-- Campaign Form -->
            <div id="campaignForm" class="hidden" style="display: <%=campaignFormDisplay%>">
                <h3>Create/Edit Campaign</h3>
                <form action="admin.jsp" method="post" id="campaignFormInner">
                    <input type="hidden" name="action" value="<%=(request.getParameter("editCampaignId") != null) ? "update" : "create"%>"/>
                    <div class="form-group">
                        <label for="campaignId">Campaign ID (for Edit):</label>
                        <input type="number" id="campaignId" name="campaignId" value="<%= (request.getParameter("editCampaignId") != null) ? request.getParameter("editCampaignId") : ""%>" readonly placeholder="Leave blank for new">
                    </div>
                    <div class="form-group">
                        <label for="title">Title:</label>
                        <input type="text" id="title" name="title" value="<%= (request.getParameter("titleEdit") != null) ? request.getParameter("titleEdit") : ""%>" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" required><%= (request.getParameter("descriptionEdit") != null) ? request.getParameter("descriptionEdit") : ""%></textarea>
                    </div>
                    <div class="form-group">
                        <label for="goalAmount">Goal Amount:</label>
                        <input type="number" id="goalAmount" name="goalAmount" step="0.01" value="<%= (request.getParameter("goalAmountEdit") != null) ? request.getParameter("goalAmountEdit") : ""%>" required>
                    </div>
                    <div class="form-group">
                        <label for="collectedAmount">Collected Amount:</label>
                        <input type="number" id="collectedAmount" name="collectedAmount" step="0.01" value="<%= (request.getParameter("collectedAmountEdit") != null) ? request.getParameter("collectedAmountEdit") : ""%>" required>
                    </div>
                    <div class="form-group">
                        <label for="startDate">Start Date:</label>
                        <input type="date" id="startDate" name="startDate" value="<%= (request.getParameter("startDateEdit") != null) ? request.getParameter("startDateEdit") : ""%>" required>
                    </div>
                    <div class="form-group">
                        <label for="endDate">End Date:</label>
                        <input type="date" id="endDate" name="endDate" value="<%= (request.getParameter("endDateEdit") != null) ? request.getParameter("endDateEdit") : ""%>" required>
                    </div>
                    <div class="form-group">
                        <label for="status">Status:</label>
                        <select id="status" name="status">
                            <option value="active" <% if ("active".equals(request.getParameter("statusEdit"))) { %> selected <% } %>>Active</option>
                            <option value="completed" <% if ("completed".equals(request.getParameter("statusEdit"))) { %> selected <% } %>>Completed</option>
                        </select>
                    </div>
                    <div class="form-actions">
                        <button type="submit" class="btn btn-success">Save Campaign</button>
                        <button type="button" class="btn btn-warning" onclick="document.getElementById('campaignForm').style.display='none'">Cancel</button>
                    </div>
                </form>
            </div>

            <!-- Campaign Listing -->
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Goal</th>
                    <th>Collected Amount</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <% if (campaigns != null && !campaigns.isEmpty()) {
                    for (Campaigns campaign : campaigns) { %>
                <tr>
                    <td><%= campaign.getId() %></td>
                    <td><%= campaign.getTitle() %></td>
                    <td><%= campaign.getGoalAmount() %></td>
                    <td><%= campaign.getCollectedAmount() %></td>
                    <td><%= dateFormat.format(campaign.getStartDate()) %></td>
                    <td><%= dateFormat.format(campaign.getEndDate()) %></td>
                    <td><%= campaign.getStatus() %></td>
                    <td>
                        <a href="?showSection=campaigns&editCampaignId=<%= campaign.getId() %>&titleEdit=<%= campaign.getTitle() %>&descriptionEdit=<%= campaign.getDescription() %>&goalAmountEdit=<%= campaign.getGoalAmount() %>&collectedAmountEdit=<%=campaign.getCollectedAmount()%>&startDateEdit=<%= dateFormat.format(campaign.getStartDate()) %>&endDateEdit=<%= dateFormat.format(campaign.getEndDate()) %>&statusEdit=<%= campaign.getStatus() %>&campaignFormDisplay=block">Edit</a>
                        <a href="?action=delete&campaignId=<%= campaign.getId() %>">Delete</a>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="8">No campaigns found.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>


    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>