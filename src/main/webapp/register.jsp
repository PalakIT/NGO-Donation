<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="register.css">
</head>
<style>
/* Common Styles */

</style>
<body>

<nav class="navbar">
        <div class="logo">Care<span>Foundation</span></div>
        <div class="nav-links">
            <a href="index.jsp" class="active">Home</a>
            <a href="about.jsp">About Us</a>
            <a href="contact.jsp">Contact</a>
            <a href="campaigns.jsp">Campaigns</a>
            <a href="donate.jsp" class="donate-btn">Donate Now</a>
            <button onclick="window.location.href='login.jsp'" class="login-btn">Login</button>
        </div>
        <div class="hamburger">
            <span></span>
            <span></span>
            <span></span>
        </div>
    </nav>
    <div class="container">

        <div class="card">
            <div>

                <h2 class="card-title">Create Account</h2>
                <p class="card-subtitle">Choose your role to register</p>
            </div>

            <!-- Donor Registration Form -->
            <form id="donorForm" action="register" method="post">

            <%-- <div class="form-group">
                           <label for="roleSelect">Register as:</label>
                           <select id="roleSelect" name="roleSelect" class="form-select" onchange="toggleForm()">
                               <option value="donor">Register as Donor</option>
                               <option value="ngos">Register as NGO</option>
                           </select>
                       </div> --%>

                <div class="form-group">
                    <label class="form-label">Full Name</label>
                    <input type="text" name="name" class="form-control" required>
                </div>

                <div class="form-group">
                    <label class="form-label">Email Address</label>
                    <input type="email" name="email" class="form-control" required>
                </div>

                <div class="form-group">
                    <label class="form-label">Phone Number</label>
                    <input type="tel" name="phone" class="form-control" required>
                </div>

                <div class="form-group">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                  <div class="form-group">
                                    <label class="form-label">Aadhar Card</label>
                                    <input type="tel" name="aadhar" class="form-control" required>
                                </div>

                <div class="form-group">
                    <label class="form-label">Location</label>
                    <input type="text" name="location" class="form-control" required>
                </div>

                <button type="submit" class="btn btn-primary">Register </button>
            </form>



            <p class="footer-text">
                Already have an account?
                <a href="login.jsp" class="footer-link">Login here</a>
            </p>
        </div>
    </div>

    <script src="register.js"></script>
</body>
</html>