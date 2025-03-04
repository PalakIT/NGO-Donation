<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
  footer {
    background: var(--dark-gray);
    color: white;
    padding: 3rem 5% 1rem;
   }

.footer-content {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 2rem;
    margin-bottom: 2rem;
}

.footer-section h3 {
    margin-bottom: 1rem;
}

.footer-section a {
    color: white;
    text-decoration: none
}

.footer-bottom{
    justify-content: center;
    text-align: center;
}
</style>
<footer>
  <div class="footer-content">
            <div class="footer-section">
                            <h1>Care Foundation</h1>
                            <p>Care Foundation is a global humanitarian organization dedicated to fighting poverty , providing disaster relief and promoting social justice . it focus on education, healthcare,sustainable development.</p>

             </div>
            <div class="footer-section">
                <h3>Quick Links</h3>
                <a href="about.jsp">About Us</a> <br>
                <a href="home.jsp">Home</a><br>
                <a href="donate.jsp">Donate</a><br>
                <a href="contact.jsp">Contact Us</a><br>
            </div>

            <div class="footer-section">
                            <h3>Contact Us</h3>
                            <p>Email: carefoundation@gmail.com</p>
                            <p>Phone: +123456789</p>
                            <p>Address: 123 Charity Lane, Helping City</p>
                        </div>

        </div>
        <div class="footer-bottom">
            <p>© 2025 Care Foundation. All rights reserved.</p>
        </div>

        </footer>