package com.kotrkv.mentor.jsp.controllers;

import com.kotrkv.mentor.jsp.model.User;
import com.kotrkv.mentor.jsp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> allParams, HttpSession session, Model model) {
        String login = allParams.get("login");
        String password = allParams.get("password");

        if (userService.findByLoginAndPassword(login, password).isPresent()) {
            User user = userService.findByLoginAndPassword(login, password).get();
            session.setAttribute("user", user);

            if (user.getRole().equalsIgnoreCase("admin")) {
                return "redirect:/admin";
            } else {
                System.out.println("------>>>> /user");
                return "redirect:/user";
            }
        } else {
            System.out.println("redirect:/login");
            return "redirect:/error";
        }
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("error", "User not found");
        return "errorPage";
    }
}
