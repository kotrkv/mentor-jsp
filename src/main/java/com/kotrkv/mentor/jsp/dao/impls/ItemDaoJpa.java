package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.ItemDao;
import com.kotrkv.mentor.jsp.model.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ItemDaoJpa implements ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Item> getAll() {
        return entityManager.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }

    @Override
    public void create(Item item) {
        entityManager.persist(item);
    }

    @Override
    public void delete(Integer id) {
        Optional<Item> item = Optional.of(entityManager.find(Item.class, id));
        entityManager.remove(item.get());
    }

    @Override
    public void update(Item item) {
        entityManager.merge(item);
    }

    @Override
    public Optional<Item> getByName(String name) {
        TypedQuery<Item> query = entityManager.createQuery("SELECT i FROM Item i WHERE i.name = :name", Item.class);
        query.setParameter("name", name);
        List<Item> roles = query.getResultList();
        return roles.size() > 0 ? Optional.of((Item)query.getSingleResult()) : Optional.empty();
    }

    @Override
    public Optional<Item> getById(Integer id) {
        TypedQuery<Item> query = entityManager.createQuery("SELECT i FROM Item i WHERE i.id = :id", Item.class);
        query.setParameter("id", id);
        List<Item> roles = query.getResultList();
        return roles.size() > 0 ? Optional.of((Item)query.getSingleResult()) : Optional.empty();
    }
}
