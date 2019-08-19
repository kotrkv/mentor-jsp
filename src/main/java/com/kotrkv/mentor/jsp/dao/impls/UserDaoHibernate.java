package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.UserDao;
import com.kotrkv.mentor.jsp.model.User;

import java.util.List;
import java.util.Optional;

public class UserDaoHibernate implements UserDao {
    @Override
    public void create(User user) {

    }

    @Override
    public Optional<User> getById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(User user) {

    }
}
