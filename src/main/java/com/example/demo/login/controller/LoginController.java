package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    // Get Controller for login page
    @GetMapping("/login")
    public String getLogin(Model model) {

        // login.htmlの画面遷移
        return "/login/login";
    }

    // Post Controller for login page
    @PostMapping("/login")
    public String postLogin(Model model) {

        // ホーム画面に遷移
        return "/login/home";
    }
}
