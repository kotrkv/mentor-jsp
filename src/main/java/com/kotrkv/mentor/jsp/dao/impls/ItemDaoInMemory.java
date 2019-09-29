package com.kotrkv.mentor.jsp.dao.impls;

import com.kotrkv.mentor.jsp.dao.ItemDao;
import com.kotrkv.mentor.jsp.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class ItemDaoInMemory implements ItemDao {

    private List<Item> items;

    {
        items = new ArrayList<>();
        items.add(new Item(1, "CE101"));
        items.add(new Item(2, "CE102"));
        items.add(new Item(3, "CE103"));
    }
    @Override
    public List<Item> getAll() {
        return items;
    }

    @Override
    public void create(Item item) {
        items.add(item);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Item item) {

    }

    @Override
    public Optional<Item> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Item> getById(Integer id) {
        return Optional.empty();
    }
}
