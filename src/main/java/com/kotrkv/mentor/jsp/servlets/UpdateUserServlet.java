package com.kotrkv.mentor.jsp.servlets;

import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = service.findById(Integer.parseInt(req.getParameter("id"))).get();
        req.setAttribute("user", user);
        req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = service.findById(Integer.parseInt(req.getParameter("id"))).get();

            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            user.setEmail(req.getParameter("email"));
            user.setBirthday(LocalDate.parse(req.getParameter("birthday")));
            service.update(user);
            resp.sendRedirect("/users");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/errorPage.jsp").forward(req, resp);
        }
    }
}
