<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Care Foundation - Empowering Lives</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <nav class="navbar">
        <div class="logo">Care<span>Foundation</span></div>
        <div class="nav-links">

            <a href="login.jsp" class="donate-btn">Donate Now</a>
            <button onclick="window.location.href='login.jsp'" class="login-btn">Login</button>
        </div>
        <div class="hamburger">
            <span></span>
            <span></span>
            <span></span>
        </div>
    </nav>
 <section>
 <div class="image-container">
  <img src="images/home.jpg" alt="NGO Impact">
 </div>
 </section>
        <!-- Donation Section  -->

        <section class="donation-steps">
            <h2 class="donate">Donate with Ease</h2>
            <div class="steps-container">
                <div class="step-box">
                    <div class="step-icon">
                        <i class="icon-select"></i>
                    </div>
                    <h3>Choose a Cause</h3>
                    <p>Browse through our various initiatives and select the one that aligns with your values—whether it's education, healthcare, poverty relief, or environmental sustainability.</p>
                </div>
                <div class="step-box">
                    <div class="step-icon">
                        <i class="icon-donate"></i>
                    </div>
                    <h3>Make a Donation</h3>
                    <p>Contribute securely through our trusted payment system, supporting your chosen cause via
                        <br> Credit/Debit Card
                       <br>  UPI/Digital Wallets
                      <br>  Bank Transfer</p>
                </div>
                <div class="step-box">
                    <div class="step-icon">
                        <i class="icon-track"></i>
                    </div>
                    <h3>Get Your Receipt</h3>
                    <p>Once your donation is processed, you’ll receive a confirmation message and a heartfelt thank-you from our team, acknowledging your support in making a real difference.</p>
                </div>
            </div>
        </section>

        <!-- Image section -->

        <div class="container">
            <div class="flex-container">
                <!-- Image Section -->
                <div class="image-section">

                    <div class="image-container">
                        <img src="images/about_ngo.jpg" alt="NGO Impact">
                    </div>

                </div>

                <!-- About Section -->
                <div class="about-section">
                    <h2 class="section-title">About Our NGO</h2>
                    <div class="about-content">
                        <p class="description">
                            We are a dedicated non-profit organization committed to making a meaningful impact in society by addressing critical issues such as education, healthcare, poverty alleviation, and environmental sustainability. Our mission is to empower communities, uplift underprivileged individuals, and create sustainable change through impactful initiatives and generous contributions from supporters like you.
                            With transparency, accountability, and compassion at our core, we ensure that every donation reaches the people who need it most. Join us in our mission to make the world a better place—one step at a time. Care Foundation is a dedicated non-governmental organization committed to improving the lives of vulnerable communities through a variety of impactful programs. With a focus on health, education, and social welfare, Care Foundation works tirelessly to address pressing issues such as poverty, illiteracy, and access to healthcare.Their unwavering commitment to humanitarian values makes them a vital force for positive change in the communities they serve.
                        </p>
                        <ul class="features-list">
                            <li>Education and skill development programs</li>
                            <li>Healthcare initiatives and medical camps</li>
                            <li>Environmental conservation projects</li>
                            <li>Community development programs</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <!-- Donation Section -->
        <section id="donate-section" class="donation-section">
            <div class="container">
                <h2 class="section-title">Make a Donation</h2>
                <div class="donation-wrapper">
                    <div class="donation-form">
                        <div class="amount-selector">
                            <h3>Select Amount</h3>
                            <div class="amount-options">
                                <button class="amount-btn"> ₹ 1000</button>
                                <button class="amount-btn"> ₹ 2000</button>
                                <button class="amount-btn active"> ₹ 5000</button>
                                <button class="amount-btn"> ₹ 10000</button>
                                <input type="number" placeholder="Custom Amount" class="custom-amount">
                            </div>
                        </div>

                        <div class="donation-details">
                            <h3>Personal Information</h3>
                            <form id="donationForm">
                                <div class="form-row">
                                    <div class="form-group">
                                        <input type="text" placeholder="Full Name" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="email" placeholder="Email" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <input type="tel" placeholder="Phone Number" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" placeholder="PAN Number (Optional)">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <select required>
                                        <option value="">Select Cause</option>
                                        <option value="education">Education</option>
                                        <option value="healthcare">Healthcare</option>
                                        <option value="covid">COVID Relief</option>
                                    </select>
                                </div>
                                <button type="submit" onclick="window.location.href='login.jsp'" class="donate-submit-btn">Proceed to Pay</button>
                            </form>
                        </div>
                    </div>
                    <div class="donation-info">
                        <div class="info-card">
                            <h3>Why Donate?</h3>
                            <ul>
                                <li>Support quality education for underprivileged children</li>
                                <li>Provide healthcare services to rural communities</li>
                                <li>Enable sustainable development initiatives</li>
                            </ul>
                        </div>
                        <div class="tax-benefits">
                            <h3>Tax Benefits</h3>
                            <p>All donations are eligible for tax deduction under 80G</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </main>

    <jsp:include page="includes/footer.jsp"/>
    <script src="script.js"></script>
</body>
</html>