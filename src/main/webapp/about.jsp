
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
    <title>About Us - Care Foundation</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <!-- Navbar-->
    <nav class="navbar">
        <div class="logo">Care<span>Foundation</span></div>
        <div class="nav-links">
            <a href="home.jsp" >Home</a>
            <a href="about.jsp"class="active">About Us</a>
            <a href="contact.jsp">Contact</a>
            <a href="campaign?action=list">Campaigns</a>
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

    <main class="about-page">
        <!-- Hero Section -->
        <section class="about-hero">
        </section>

        <!-- Mission & Vision -->
        <section class="mission-vision">
            <div class="container">
                <div class="mission-box">
                    <h2>Our Mission</h2>
                    <p>Our mission is to empower communities and transform lives by providing essential support in areas like education, healthcare, poverty alleviation, and environmental sustainability. We strive to create lasting change by fostering opportunities, ensuring access to basic needs, and building a society where everyone has the chance to thrive</p>

                  </div>

        <div class="vision-box">
         <h2>Our Vision</h2>
            <p>We envision a world where every individual has equal opportunities to grow, learn, and live with dignity. Our goal is to become a driving force for positive transformation, ensuring a future where compassion, sustainability, and social equity lead the way.</p>
           </div>
        </div>
                </div>


            </div>
        </section>
        <!-- Impact Numbers -->
        <section class="impact-numbers">
            <div class="container">
                <h2>Our Impact</h2>
                <div class="impact-grid">
                    <div class="impact-card">
                        <span class="number">50,000+</span>
                        <p>Children Educated</p>
                    </div>
                    <div class="impact-card">
                        <span class="number">100+</span>
                        <p>Schools Built</p>
                    </div>
                    <div class="impact-card">
                        <span class="number">200,000+</span>
                        <p>Medical Treatments</p>
                    </div>
                    <div class="impact-card">
                        <span class="number">1,000+</span>
                        <p>Villages Reached</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Our Story -->
        <section class="our-story">
            <div class="container">
                <div class="story-content">
                    <h2>Our Story</h2>
                    <p>Founded in 2010, Care Foundation began with a simple mission: to provide quality education to underprivileged children. What started as a small initiative in one village has now grown into a nationwide movement, touching millions of lives.</p>
                    <p>Our journey has been marked by countless stories of transformation, resilience, and hope. From building schools in remote areas to providing healthcare in underserved communities, we've remained committed to our core values of compassion, integrity, and excellence.</p>
                </div>
                <div class="story-image">
                    <img src="images/story.jpg" alt="Our Journey">
                </div>
            </div>
        </section>

        <!-- Team Section -->
        <section class="team-section">
            <div class="container">
                <h2>Our Leadership Team</h2>
                <div class="team-grid">
                    <div class="team-member">
                        <img src="images/team1.jpg" alt="Team Member">
                        <h3>John Doe</h3>
                        <p>Founder & CEO</p>
                    </div>
                    <div class="team-member">
                        <img src="images/team2.jpg" alt="Team Member">
                        <h3>Jane Smith</h3>
                        <p>Director of Operations</p>
                    </div>
                    <div class="team-member">
                        <img src="images/team3.jpg" alt="Team Member">
                        <h3>Mike Johnson</h3>
                        <p>Head of Programs</p>
                    </div>
                </div>
            </div>
        </section>
    </main>
       <div class="F">
       <jsp:include page="includes/footer.jsp"/>
       </div>

</body>
</html>