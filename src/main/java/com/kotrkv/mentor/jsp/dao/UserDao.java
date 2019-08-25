package com.kotrkv.mentor.jsp.dao;

import com.kotrkv.mentor.jsp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void create(User user);
    Optional<User> getById(Integer id);
    Optional<User> getByLoginAndPassword(String login, String password);
    List<User> getAll();
    void delete(Integer id);
    void update(User user);
}
