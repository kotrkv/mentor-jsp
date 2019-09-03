package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.dao.UserDao;
import com.kotrkv.mentor.jsp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao daoUser;

    public UserService() {

    }

//    @Autowired
//    public UserService(UserDao userDao) {
//        this.daoUser = userDao;
//    }

    public List<User> findAll() {
        return daoUser.getAll();
    }

    public void add(User user) {
        daoUser.create(user);
    }

    public Optional<User> findById(Integer id) {
        return daoUser.getById(id);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return daoUser.getByLoginAndPassword(login, password);
    }

    public void update(User user) {
        daoUser.update(user);
    }

    public void delete(Integer id) {
        daoUser.delete(id);
    }
}
