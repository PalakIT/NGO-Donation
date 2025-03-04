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
    <title>Contact Us - Care Foundation</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <!-- Navbar (same as other pages) -->
    <nav class="navbar">
        <div class="logo">Care<span>Foundation</span></div>
        <div class="nav-links">
            <a href="home.jsp" >Home</a>
            <a href="about.jsp">About Us</a>
            <a href="contact.jsp" class="active">Contact</a>
            <a href="campaign.jsp">Campaigns</a>
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

    <main class="contact-page">
        <!-- Contact Hero -->
        <section class="contact-hero">
            <div class="hero-content">
                <h1>Get in Touch</h1>
                <p>We'd love to hear from you</p>
            </div>
        </section>

        <!-- Contact Information -->
        <section class="contact-info">
            <div class="container">
                <div class="info-grid">
                    <div class="info-card">
                        <div class="icon">
                            <i class="fas fa-map-marker-alt"></i>
                        </div>
                        <h3>Visit Us</h3>
                        <p>123 Charity Lane</p>
                        <p>Helping City, HC 12345</p>
                    </div>
                    <div class="info-card">
                        <div class="icon">
                            <i class="fas fa-phone"></i>
                        </div>
                        <h3>Call Us</h3>
                        <p>+123456789</p>
                        <p>Mon - Fri, 9am - 6pm</p>
                    </div>
                    <div class="info-card">
                        <div class="icon">
                            <i class="fas fa-envelope"></i>
                        </div>
                        <h3>Email Us</h3>
                        <p>carefoundation@gmail.com</p>
                        <p>support@carefoundation.org</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Contact Form -->
        <section class="contact-form-section">
            <div class="container">
                <div class="form-wrapper">
                    <h2>Send Us a Message</h2>
                    <form class="contact-form" id="contactForm">
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" id="name" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email Address</label>
                            <input type="email" id="email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone Number</label>
                            <input type="tel" id="phone" name="phone">
                        </div>
                        <div class="form-group">
                            <label for="subject">Subject</label>
                            <select id="subject" name="subject">
                                <option value="general">General Inquiry</option>
                                <option value="donation">Donation Related</option>
                                <option value="volunteer">Volunteering</option>
                                <option value="other">Other</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="message">Your Message</label>
                            <textarea id="message" name="message" rows="6" required></textarea>
                        </div>
                        <button type="submit" class="submit-btn">Send Message</button>
                    </form>
                </div>
            </div>
        </section>

        <!-- FAQ Section -->
        <section class="faq-section">
            <div class="container">
                <h2>Frequently Asked Questions</h2>
                <div class="faq-grid">
                    <div class="faq-item">
                        <h3>How can I donate?</h3>
                        <p>You can donate through our secure online platform, bank transfer, or by visiting our office.</p>
                    </div>
                    <div class="faq-item">
                        <h3>How is my donation used?</h3>
                        <p>Your donation is used to support various programs, including education, healthcare, disaster relief, and community development. We ensure that funds are allocated efficiently to maximize the impact and help those in need.</p>
                    </div>
                    <div class="faq-item">
                        <h3> Are online donations secure?</h3>
                        <p>Yes, all online donations are processed through secure payment gateways with encryption to protect your personal and financial information. We do not store sensitive payment details.</p>
                    </div>

                    <div class="faq-item">
                        <h3> Can I cancel or get a refund for my donation?</h3>
                        <p>Donations are generally non-refundable. However, if you made an accidental donation or faced an issue, please contact our support team, and we will review your request based on our refund policy.</p>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="includes/footer.jsp"/>

    <script src="script.js"></script>
</body>
</html>