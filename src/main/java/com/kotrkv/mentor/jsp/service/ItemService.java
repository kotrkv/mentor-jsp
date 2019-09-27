package com.kotrkv.mentor.jsp.service;

import com.kotrkv.mentor.jsp.dao.ItemDao;
import com.kotrkv.mentor.jsp.model.Item;
import com.kotrkv.mentor.jsp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemDao daoItem;

    public ItemService() {

    }

    public List<Item> findAll() {
        return daoItem.getAll();
    }

    public void add(Item item) {
        daoItem.create(item);
    }

    public Optional<Item> findById(Integer id) {
        return daoItem.getById(id);
    }

    public void update(Item item) {
        daoItem.update(item);
    }

    public void delete(Integer id) {
        daoItem.delete(id);
    }
}
