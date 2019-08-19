package com.kotrkv.mentor.jsp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Date;

@Entity
@Table(name = "users", schema = "jsp_project")
public class User {
    private static int COUNTER = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="birthday")
    private Date birthday;

    public User() {
    }

    public User(String login, String password, String email, Date birthday) {
        this.id = COUNTER++;
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    public User(Integer id, String login, String password, String email, Date birthday) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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
