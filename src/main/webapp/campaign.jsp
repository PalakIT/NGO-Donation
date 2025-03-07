<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if(session.getAttribute("email") == null){
    response.sendRedirect("login.jsp");
    return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
     <link rel="stylesheet" href="campaign.css">
    <title>Available Campaigns</title>

</head>
<body>
<nav class="navbar">
          <div class="logo">Care<span>Foundation</span></div>
          <div class="nav-links">
              <a href="home.jsp">Home</a>
              <a href="about.jsp">About Us</a>
              <a href="contact.jsp" >Contact</a>
              <a class="active" href = "campaign?action=list">Campaigns</a>
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

<h1>Available Campaigns</h1>

<div class="campaign-container">
    <c:if test="${not empty campaigns}">
        <c:forEach var="campaign" items="${campaigns}">
            <div class="campaign-box">
                <div class="campaign-title"><c:out value="${campaign.title}" /></div>

                <div class="goal-amount">
                    <c:out value="${campaign.goalAmount}" />
                </div>

                <div class="campaign-description"><c:out value="${campaign.description}" /></div>

                <!-- Progress bar -->
                <div class="progress-bar">
                    <div class="progress" style="width: ${(campaign.collectedAmount / campaign.goalAmount) * 100}%"></div>
                </div>

                <div class="campaign-details">
                    <strong>Collected Amount:</strong> <c:out value="${campaign.collectedAmount}" /><br>
                    <strong>Start Date:</strong> <c:out value="${campaign.startDate}" /><br>
                    <strong>End Date:</strong> <c:out value="${campaign.endDate}" /><br>
                    <strong>Status:</strong> <c:out value="${campaign.status}" />
                </div>

                <button class="donate-button" onclick="location.href='donate.jsp?campaignId=${campaign.id}'">
                    Donate Now
                </button>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${empty campaigns}">
        <p class="no-campaigns">No campaigns found.</p>
    </c:if>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>