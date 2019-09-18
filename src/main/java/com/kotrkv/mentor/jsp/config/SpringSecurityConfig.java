package com.kotrkv.mentor.jsp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/user").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .and()
                .authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .authorizeRequests().antMatchers("/", "/resources/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/")
                .permitAll()
                .usernameParameter("login")
                .loginProcessingUrl("/login")
                .successForwardUrl("/auth")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");

    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }
}
