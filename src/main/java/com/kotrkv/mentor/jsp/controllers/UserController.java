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
import java.util.HashSet;
import java.util.Set;

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
        System.out.println("/auth");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equalsIgnoreCase("ADMIN"))) {
            System.out.println("ok");
            return "redirect:/admin";
        } else {
            System.out.println("This is - " + authentication.getName());
            session.setAttribute("user", authentication.getName());
            return "redirect:/user";
        }
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        System.out.println("/admin");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
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
        model.addAttribute("roles", roleService.findAll());
        return "/editUser";
    }

    @PostMapping("/admin/editUser")
    public String editForm(@RequestParam("id") Integer id,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("roleName") String roleName) {

        System.out.println("Id - " + id);
        System.out.println("Login - " + login);
        System.out.println("Password - " + password);
        System.out.println("Email - " + email);
        System.out.println("Role - " + roleName);

        Role role = roleService.findByName(roleName).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = userService.findById(id).get();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoles(roles);

        userService.update(user);

        return "redirect:/admin";
    }

    @GetMapping("/admin/addUser")
    public String addForm(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "/addUser";
    }

    @PostMapping("/admin/addUser")
    public String add(@ModelAttribute User user, @RequestParam String roleName) {
        Role role = roleService.findByName(roleName).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
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
        System.out.println("User not found");
        model.addAttribute("error", "User not found");
        return "errorPage";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "error/403";
    }
}
