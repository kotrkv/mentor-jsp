package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.dao.UserDao;
import com.kotrkv.mentor.jsp.dao.UserDaoFactory;
import com.kotrkv.mentor.jsp.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private UserDao daoUser;

    public UserService() {
        daoUser = new UserDaoFactory().createUserDao();
    }

    public List<User> findAll() {
        return daoUser.getAll();
    }

    public void add(User user) {
        daoUser.create(user);
    }

    public Optional<User> findById(Integer id) {
        return daoUser.getById(id);
    }

    public void update(User user) {
        daoUser.update(user);
    }

    public void delete(Integer id) {
        daoUser.delete(id);
    }
}
