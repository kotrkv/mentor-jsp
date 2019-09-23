package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.Role;
import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.RoleService;
import com.kotrkv.mentor.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/user")
    public String getUser(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/admin")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/admin/addUser")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/editUser")
    public ResponseEntity<Void> editUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.noContent().build();
    }
}
