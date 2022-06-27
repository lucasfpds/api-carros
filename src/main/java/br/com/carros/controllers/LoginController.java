package br.com.carros.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String login() {
        return "Login";
    }

    @GetMapping("/login")
    public String login(String login, String senha) {
        return "Login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam("login") String login, @RequestParam("senha") String senha) {
        return login + " " + senha;
    }

}
