package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.Role;
import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.AuthService;
import com.kotrkv.mentor.jsp.service.RoleService;
import com.kotrkv.mentor.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/google")
    public void test(HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.authorisationUrl());
    }

    @GetMapping("/auth/google")
    public String test(@RequestParam String code) {

        final String defaultRole = "ADMIN";
        final String name = authService.authUserName(code);

        Role role = new Role();
        role.setName(defaultRole);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setLogin(name);
        user.setRoles(roles);

        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(), user.getRoles());
        SecurityContextHolder.getContext().setAuthentication(authReq);

        return "redirect:/admin";
    }

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
            session.setAttribute("user", authentication.getName());
            return "redirect:/user";
        }
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
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
        model.addAttribute("error", "User not found");
        return "errorPage";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "error/403";
    }
}