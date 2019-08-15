package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.Dao;
import com.kotrkv.mentor.jsp.db.RuntimeDB;
import com.kotrkv.mentor.jsp.model.User;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User, Integer> {

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(User user) {
        RuntimeDB.getInstance().getUsers().add(user);
    }

    @Override
    public Optional<User> getById(Integer integer) {
        return Optional.of(RuntimeDB.getInstance().getUsers().get(integer));
    }

    @Override
    public List<User> getAll() {
        return RuntimeDB.getInstance().getUsers();
    }

    @Override
    public void delete(User user) {
        RuntimeDB.getInstance().getUsers().remove(user);
    }
}
