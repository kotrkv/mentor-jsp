package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.RoleDao;
import com.kotrkv.mentor.jsp.model.Role;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

//import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
//@Transactional
public class RoleDaoJpa implements RoleDao {

//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public List<Role> getAll() {
//        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        return null;
    }

    @Override
    public Optional<Role> getByName(String name) {
//        TypedQuery<Role> query = entityManager.createQuery("SELECT u FROM Role u WHERE u.name = :name", Role.class);
//        query.setParameter("name", name);
//        List<Role> roles = query.getResultList();
//        return roles.size() > 0 ? Optional.of((Role)query.getSingleResult()) : Optional.empty();
        return null;
    }
}
