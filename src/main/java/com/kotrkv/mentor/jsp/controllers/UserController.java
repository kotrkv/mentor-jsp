package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/listUsers";
    }

    @PostMapping("/admin/editUser")
    public String editForm(@RequestParam("id") Integer id,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email){
        User user = userService.findById(id).get();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/editUser")
    public String edit(@RequestParam(value = "id") Integer id, Model model) {
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        return "/editUser";
    }

    @GetMapping("/admin/addUser")
    public String addForm(){
        return "/addUser";
    }

    @PostMapping("/admin/addUser")
    public String add(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin/deleteUser")
    public String delete(@RequestParam Integer id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
