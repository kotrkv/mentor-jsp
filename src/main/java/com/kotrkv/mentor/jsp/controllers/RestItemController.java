package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.Item;
import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class RestItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public List<Item> getItems() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable("id") Integer id) {
        return itemService.findById(id).get();
    }

    @PostMapping("/")
    public Item addUser(@RequestBody Item item) {
        itemService.add(item);
        return item;
    }

    @PutMapping("/")
    public Item editUser(@RequestBody Item item) {
        itemService.update(item);
        return item;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Integer id) {
        itemService.delete(id);
    }
}
