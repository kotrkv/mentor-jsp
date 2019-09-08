package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        System.out.println("/login");
        return "redirect:/error";
    }

    @PostMapping("/auth")
    public String login(@RequestParam Map<String, String> allParams, HttpSession session) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();

        System.out.println(userDetails.getAuthorities());

        String login = allParams.get("login");
        String password = allParams.get("password");

        if (userService.findByLoginAndPassword(login, password).isPresent()) {
            User user = userService.findByLoginAndPassword(login, password).get();
            session.setAttribute("user", user);

            if (user.getRoles().stream().anyMatch(x -> x.getName().equalsIgnoreCase("ROLE_ADMIN"))) {
                System.out.println("after login");
                return "redirect:/admin";
            } else {
                return "redirect:/user";
            }
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        System.out.println("/admin");
        model.addAttribute("users", userService.findAll());
        return "/listUsers";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin/editUser")
    public String edit(@RequestParam(value = "id") Integer id, Model model) {
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        return "/editUser";
    }

    @PostMapping("/admin/editUser")
    public String editForm(@RequestParam("id") Integer id,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email) {
        User user = userService.findById(id).get();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/addUser")
    public String addForm() {
        return "/addUser";
    }

    @PostMapping("/admin/addUser")
    public String add(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteUser")
    public String delete(@RequestParam Integer id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("error", "User not found");
        return "errorPage";
    }
}
