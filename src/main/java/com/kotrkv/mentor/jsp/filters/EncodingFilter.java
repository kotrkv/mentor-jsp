package com.kotrkv.mentor.jsp.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = "addUserServlet")
public class EncodingFilter implements Filter {

    private final String encodidng = "utf-8";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encodidng);
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.setContentType("text/html;charset=UTF-8");
    }

    @Override
    public void destroy() {

    }
}
