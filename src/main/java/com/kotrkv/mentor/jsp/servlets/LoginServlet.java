package com.kotrkv.mentor.jsp.servlets;

import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (service.findByLoginAndPassword(req.getParameter("login"), req.getParameter("password")).isPresent()) {
            User user = service.findByLoginAndPassword(req.getParameter("login"), req.getParameter("password")).get();
                req.setAttribute("user", user);
//            req.getSession().setAttribute("isLogined", true);
            req.getSession().setAttribute("user", user);

            if (user.getRole().equalsIgnoreCase("user")) {
                req.getRequestDispatcher("/user").forward(req, resp);
            }
            if (user.getRole().equalsIgnoreCase("admin")) {
                req.getRequestDispatcher("/admin").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "User not found");
            req.getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }
    }
}
