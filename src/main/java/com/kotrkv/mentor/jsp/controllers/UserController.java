package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.Role;
import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.RoleService;
import com.kotrkv.mentor.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @PostMapping("/auth")
    public String login(HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equalsIgnoreCase("ADMIN"))) {
            return "redirect:/admin";
        } else {
            System.out.println(authentication.getName());
            session.setAttribute("user", authentication.getName());
            return "redirect:/user";
        }
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/listUsers";
    }

    @GetMapping("/user")
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.setViewName("user");
        return modelAndView;
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

        System.out.println("Id - " + id);
        System.out.println("Login - " + login);
        System.out.println("Password - " + password);
        System.out.println("Email - " + email);

        User user = userService.findById(id).get();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/addUser")
    public String addForm(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "/addUser";
    }

    @PostMapping("/admin/addUser")
    public String add(@ModelAttribute User user, @ModelAttribute Role role) {
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

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        model.addAttribute("error", "Access denied for " + SecurityContextHolder.getContext().getAuthentication().getName());
        return "errorPage";
    }
}
