package com.kotrkv.mentor.jsp.dao;

import com.kotrkv.mentor.jsp.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    List<Item> getAll();
    void create(Item item);
    void delete(Integer id);
    void update(Item item);

    Optional<Item> getByName(String name);
    Optional<Item> getById(Integer id);
}
