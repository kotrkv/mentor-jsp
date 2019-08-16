package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.dao.Dao;
import com.kotrkv.mentor.jsp.dao.impls.UserDao;
import com.kotrkv.mentor.jsp.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private Dao<User, Integer> daoUser;

    public UserService() {
        daoUser = UserDao.getInstance();
    }

    public List<User> findAll() {
        return daoUser.getAll();
    }

    public void add(User user) {
        daoUser.create(user);
    }

    public Optional<User> findById(Integer id) {
        return UserDao.getInstance().getById(id);
    }

    public void update(User user) {
        UserDao.getInstance().update(user);
    }

    public void delete(Integer id) {
        UserDao.getInstance().delete(id);
    }
}
