package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {

        return userService.findById(id).get();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @PutMapping("/user")
    public User editUser(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
    }
}
