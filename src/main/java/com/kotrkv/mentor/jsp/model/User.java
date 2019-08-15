package com.kotrkv.mentor.jsp.model;

import java.time.LocalDate;

public class User {
    private static int COUNTER = 1;

    private Integer id;
    private String login;
    private String password;
    private String email;
    private LocalDate birthday;

    public User() {
    }

    public User(String login, String password, String email, LocalDate birthday) {
        this.id = COUNTER++;
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
