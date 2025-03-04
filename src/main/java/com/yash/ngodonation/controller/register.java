package com.yash.ngodonation.controller;

import com.yash.ngodonation.service.RegisterService;
import com.yash.ngodonation.serviceimplementation.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class register extends HttpServlet {

    private RegisterService registerService;

    @Override
    public void init() throws ServletException {
        // Initialize the service in the init method.  Good practice.
        registerService = new RegisterServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Use the Service Layer
        //RegisterService registerService = new RegisterServiceImpl();  // Or inject the service if using a framework, now using init()
        //RegisterService registerService = new RegisterServiceImpl(new MyMockRegisterDao()); // For Testing - example

        try {
            // Donor Registration
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");

            // Call registerDonor through the Service Layer
            boolean isRegistered = registerService.registerDonor(name, email, phone, password);

            if (isRegistered) {
                req.setAttribute("successMessage", "Registration successful!");
                req.getRequestDispatcher("login.jsp").forward(req, resp); // Forward to login page
            } else {
                req.setAttribute("errorMessage", "Registration failed. Please try again.");
                req.getRequestDispatcher("register.jsp").forward(req, resp); // Forward back to registration form
            }

        } catch (IllegalArgumentException e) {  // Catch validation errors from service layer
            req.setAttribute("errorMessage", e.getMessage()); // Specific error message
            req.getRequestDispatcher("register.jsp").forward(req, resp); // Back to registration form
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error!  Crucial for debugging
            req.setAttribute("errorMessage", "Database error occurred: " + e.getMessage()); // Include error message
            req.getRequestDispatcher("register.jsp").forward(req, resp); // Or an error page
        }
    }
}