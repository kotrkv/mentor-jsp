package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.UserDao;
import com.kotrkv.mentor.jsp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Repository
@Transactional
public class UserDaoHibernate implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        }
    }

    @Override
    public Optional<User> getById(Integer id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = session.get(User.class, id);
            return Optional.of(user);
        }
    }

    @Override
    public Optional<User> getByLoginAndPassword(String login, String password) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Query query = session.createQuery("FROM User u WHERE u.login = :login AND u.password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<User> users = (List<User>)query.getResultList();
            return users.size() > 0 ? Optional.of((User)query.getSingleResult()) : Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        try (Session session = sessionFactory.getCurrentSession()) {
            users = session.createQuery("from User", User.class).getResultList();
        }
        return users;
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.saveOrUpdate(user);
        }
    }
}
