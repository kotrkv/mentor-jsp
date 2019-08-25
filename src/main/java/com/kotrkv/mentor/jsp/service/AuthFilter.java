package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        if (login != null && password != null) {
            User user = userService.findByLoginAndPassword(login, password).get();
            System.out.println(user);
        }

//        httpServletResponse.sendRedirect("/login");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
