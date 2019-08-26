package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter({"/login", "/admin/*", "/user"})
@WebFilter("/*")
public class AuthFilter implements Filter {

    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = new UserService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String uri = httpServletRequest.getRequestURI();

        System.out.println("uri - " + uri);

        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");

        HttpSession session = httpServletRequest.getSession();
        System.out.println("Session - " + session.getAttribute("user"));
        System.out.println("Login - " + login + ", password - " + password);

        if (login != null && password != null) {

            if (userService.findByLoginAndPassword(login, password).isPresent()) {
                User user = userService.findByLoginAndPassword(login, password).get();

                httpServletRequest.getSession().setAttribute("user", user);

                if (user.getRole().equalsIgnoreCase("user")) {
//                    httpServletRequest.setAttribute("user", user);
//                    httpServletRequest.getRequestDispatcher("/user").forward(servletRequest, servletResponse);
                    httpServletResponse.sendRedirect("/user");
                    return;
                }
                if (user.getRole().equalsIgnoreCase("admin")) {
//                    httpServletRequest.setAttribute("user", user);
//                    httpServletRequest.getRequestDispatcher("/admin").forward(servletRequest, servletResponse);
                    httpServletResponse.sendRedirect("/admin");
                    return;
                }
            }
        }

        User user = (User)httpServletRequest.getSession().getAttribute("user");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
