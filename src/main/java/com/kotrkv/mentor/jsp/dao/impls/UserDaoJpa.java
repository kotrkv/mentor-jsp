package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.UserDao;
import com.kotrkv.mentor.jsp.model.User;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

//import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
//@Transactional
public class UserDaoJpa implements UserDao {

    @PersistenceContext()
//    private EntityManager entityManager;

    @Override
    public void create(User user) {
//        entityManager.persist(user);
    }

    @Override
    public Optional<User> getById(Integer id) {

//        return Optional.of(entityManager.find(User.class, id));
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLoginAndPassword(String login, String password) {
//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class);
//        query.setParameter("login", login);
//        query.setParameter("password", password);
//        List<User> users = query.getResultList();
//        return users.size() > 0 ? Optional.of((User)query.getSingleResult()) : Optional.empty();
        return null;
    }

    @Override
    public Optional<User> getByLogin(String login) {
//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
//        query.setParameter("login", login);
//        List<User> users = query.getResultList();
//        return users.size() > 0 ? Optional.of((User)query.getSingleResult()) : Optional.empty();
        return null;
    }

    @Override
    public List<User> getAll() {
//        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        return null;
    }

    @Override
    public void delete(Integer id) {
//        Optional<User> user = Optional.of(entityManager.find(User.class, id));
//        entityManager.remove(user.get());
    }

    @Override
    public void update(User user) {
//        entityManager.merge(user);
    }
}
