package com.kotrkv.mentor.jsp.config;

import com.kotrkv.mentor.jsp.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
//@ComponentScan("com.kotrkv.mentor.jsp.security")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("333333333333333333333333333");
//        auth
//                .userDetailsService(userDetailsService);
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("1111111111111111111111111111111");
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                //.antMatchers("/").anonymous()
                .anyRequest().authenticated()
                .antMatchers("/login**").authenticated()
                .antMatchers("/admin**").authenticated();

        //.antMatchers("/user").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("333333333333333333333333333");
        auth
                .userDetailsService(userDetailsService);
//        System.out.println("333333333333333333333333333");
        //auth.userDetailsService(userDetailsService);
    }
}
