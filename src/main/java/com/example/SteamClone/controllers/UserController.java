package com.example.SteamClone.controllers;

import com.example.SteamClone.models.User;
import com.example.SteamClone.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(HttpSession session)
    {
        session.invalidate();
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/persAcc")
    public String persAcc(Principal principal, Model model)
    {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "persAcc";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model, boolean seller)
    {
        if (!userService.createUser(user, seller)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("games", user.getGames());
        return "user-info";
    }
    @PostMapping("/user/editBal/{id}")
    public String editBal(@PathVariable Long id, int balance)
    {
        userService.changeUserBalance(id, balance);
        return "redirect:/persAcc";
    }
}
