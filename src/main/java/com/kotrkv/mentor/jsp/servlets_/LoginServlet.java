package com.kotrkv.mentor.jsp.servlets_;

import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (service.findByLoginAndPassword(req.getParameter("login"), req.getParameter("password")).isPresent()) {
                HttpSession session = req.getSession();
                User user = service.findByLoginAndPassword(req.getParameter("login"), req.getParameter("password")).get();
                session.setAttribute("user", user);

                if (user.getRole().equalsIgnoreCase("admin")) {
                    req.getServletContext().getRequestDispatcher("/admin").forward(req, resp);
                } else {
                    req.getServletContext().getRequestDispatcher("/user").forward(req, resp);
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
    }
}
