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

/**
 * Servlet class for handling user registration requests.
 */
@WebServlet("/register")
public class register extends HttpServlet {

    /**
     * Service interface for handling registration-related operations.
     */
    private RegisterService registerService;

    /**
     * Initializes the servlet and the RegisterService.
     *
     * @throws ServletException if the servlet encounters difficulty.
     */
    @Override
    public void init() throws ServletException {
        // Initialize the service in the init method.  Good practice.
        registerService = new RegisterServiceImpl();
    }

    /**
     * Handles the HTTP <code>POST</code> method for user registration.
     *
     * @param req  HttpServletRequest object containing the client's request.
     * @param resp HttpServletResponse object for sending the response to the client.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");

            boolean isRegistered = registerService.registerDonor(name, email, phone, password);

            if (isRegistered) {
                req.setAttribute("message", "Registration successful!");
                resp.sendRedirect("login.jsp");
            } else {
                req.setAttribute("error", "Registration failed. Please try again.");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }

        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error!  Crucial for debugging
            req.setAttribute("errorMessage", "Database error occurred: " + e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp); // Or an error page
        }
    }
}