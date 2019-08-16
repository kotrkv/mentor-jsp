package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.dao.Dao;
import com.kotrkv.mentor.jsp.dao.impls.UserDao;
import com.kotrkv.mentor.jsp.dao.impls.UserDaoRuntimeDB;
import com.kotrkv.mentor.jsp.model.User;

import java.util.List;

public class UserService {
    private Dao<User, Integer> daoUser;

//    public UserService() {
//        daoUser = UserDaoRuntimeDB.getInstance();
//    }
    public UserService() {
        daoUser = UserDao.getInstance();
    }

    public List<User> findAll() {
        return daoUser.getAll();
    }

    public void add(User user) {
        daoUser.create(user);
    }
}
