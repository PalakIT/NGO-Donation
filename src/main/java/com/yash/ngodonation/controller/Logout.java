package com.yash.ngodonation.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class for handling user logout requests.
 */
@WebServlet("/LogoutServlet")
public class Logout extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method for user logout.
     * Invalidates the current session and redirects the user to the index page.
     *
     * @param request  HttpServletRequest object containing the client's request.
     * @param response HttpServletResponse object for sending the response to the client.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}