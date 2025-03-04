<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="login.css">
</head>


<body>
 <nav class="navbar">
        <div class="logo">Care<span>Foundation</span></div>
        <div class="nav-links">
          <%--  <a href="index.jsp" >Home</a>
            <a href="about.jsp">About Us</a>
            <a href="contact.jsp">Contact</a>
            <a href="campaigns.jsp">Campaigns</a>
            <a href="donate.jsp"class="active" class="donate-btn">Donate Now</a>
            <button onclick="window.location.href='login.jsp'" class="login-btn">Login</button> --%>


        </div>
        <div class="hamburger">
            <span></span>
            <span></span>
            <span></span>
        </div>
 </nav>
    <div class="container">
        <div class="card">
            <div class="logo-container">

                <h2 class="card-title">Welcome Back</h2>
                <p class="card-subtitle">Login to your account</p>
            </div>

            <form action="login" type ="submit" method="post" id="loginForm">
                <div class="form-group">
                    <label class="form-label">Email Address</label>
                    <input type="email" name="email" class="form-control" required>
                </div>

                <div class="form-group">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <button type="submit"  class="btn btn-primary">Login</button>
            </form>

            <p class="footer-text">
                Don't have an account?
                <a href="register.jsp" class="footer-link">Register here</a>
            </p>
        </div>
    </div>

    <script src="script.js"></script>
</body>
</html>