package com.yash.ngodonation.controller;

import com.yash.ngodonation.domain.Users;
import com.yash.ngodonation.service.LoginService;
import com.yash.ngodonation.serviceimplementation.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet class for handling user login requests.
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    /**
     * Service interface for handling login-related operations.
     */
    private LoginService loginService;

    /**
     * Initializes the servlet and LoginService
     * @throws ServletException if the servlet encounters difficulty
     */
    @Override
    public void init() throws ServletException {
        loginService = new LoginServiceImpl(); // Or inject the service
    }

    /**
     * Handles the HTTP <code>POST</code> method for user login.
     *
     * @param request  HttpServletRequest object containing the client's request.
     * @param response HttpServletResponse object for sending the response to the client.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Users authenticatedUser = loginService.authenticateUser(email, password);

            if (authenticatedUser != null) {
                // Login successful
                HttpSession session = request.getSession();
                session.setAttribute("id", authenticatedUser.getId());
                session.setAttribute("name", authenticatedUser.getName());
                session.setAttribute("email", authenticatedUser.getEmail());
                session.setAttribute("phone", authenticatedUser.getPhone());

                //Setting secure and HttpOnly to prevent attack at Cookies
                Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
                sessionCookie.setHttpOnly(true);
                sessionCookie.setSecure(true);  //Only transmit this cookie over HTTPS
                response.addCookie(sessionCookie);


                if (authenticatedUser.getPhone() == null) {
                    response.sendRedirect("admin.jsp"); // Admin login if phone is null
                } else {
                    response.sendRedirect("home.jsp"); // User login
                }
            } else {
                // Login failed
                request.setAttribute("errorMessage", "Invalid email or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during login. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}