package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.UserDao;
import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.DBHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Optional;

public class UserDaoHibernate implements UserDao {

    private SessionFactory sessionFactory;

    private static final UserDaoHibernate INSTANCE = new UserDaoHibernate();

    private UserDaoHibernate() {
        sessionFactory = createSessionFactory();
    }

    public static UserDaoHibernate getInstance() {
        return INSTANCE;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getInstance().createConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void create(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public Optional<User> getById(Integer id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            return Optional.of(user);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("from User", User.class).getResultList();
            transaction.commit();
        }
        return users;
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        }
    }
}
