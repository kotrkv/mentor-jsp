package com.kotrkv.mentor.jsp.config;

import com.kotrkv.mentor.jsp.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.kotrkv.mentor.jsp.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProviderImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("1111111111111111111111111111111");
        http
                .authorizeRequests()
                //.antMatchers("/").authenticated()
                .antMatchers("/").anonymous()
                .antMatchers("/admin**").authenticated();
                //.antMatchers("/user").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("333333333333333333333333333");
        auth.authenticationProvider(authProviderImpl);
    }
}
