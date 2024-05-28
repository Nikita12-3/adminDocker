package com.example.SteamClone.controllers;

import com.example.SteamClone.models.Bucket;
import com.example.SteamClone.models.Game;
import com.example.SteamClone.models.User;
import com.example.SteamClone.services.GameService;
import com.example.SteamClone.services.OrderService;
import com.example.SteamClone.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BucketController
{
    private final UserService userService;
    private final OrderService orderService;
    @GetMapping("/bucket")
    public String bucket(HttpSession session, Model model, Principal principal)
    {
        List<Game> lst = (List<Game>) session.getAttribute("lstBucket");
        model.addAttribute("games", lst);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "bucket";
    }
}
