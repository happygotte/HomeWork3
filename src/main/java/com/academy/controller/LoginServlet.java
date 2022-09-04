package com.academy.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String path = "/index.html";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            if ("Maryia".equals(name) && "12345".equals(password)) {

                writer.println("Hi " + name + "!");

                HttpSession session = request.getSession();
                session.setAttribute("user", name);

                Cookie cookie = new Cookie("testCookies", "someTestData");
                response.addCookie(cookie);

            } else {
                writer.println("Access denied");
            }
        }
    }
}