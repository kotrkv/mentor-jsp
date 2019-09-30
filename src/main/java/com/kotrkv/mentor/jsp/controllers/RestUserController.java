package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.Role;
import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.RoleService;
import com.kotrkv.mentor.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class RestUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @GetMapping("/roles/{id}")
    public Role getRole(@PathVariable Integer id) {
        return roleService.findById(id).get();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.findById(id).get();
    }

    @PostMapping("/")
    public User create(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @PutMapping("/")
    public User update(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }
}
