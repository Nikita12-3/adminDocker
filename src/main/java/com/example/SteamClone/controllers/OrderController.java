package com.example.SteamClone.controllers;

import com.example.SteamClone.models.Order;
import com.example.SteamClone.models.User;
import com.example.SteamClone.services.GameService;
import com.example.SteamClone.services.OrderService;
import com.example.SteamClone.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;
    private final UserService userService;
    @GetMapping("/orders")
    public String orders(Principal principal, Model model)
    {
        model.addAttribute("orders", orderService.listOrders(userService.getUserByPrincipal(principal)));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "orders";
    }
    @PostMapping("/order/create")
    public String createOrder(HttpSession session, Principal principal)
    {
        User us = userService.getUserByPrincipal(principal);
        orderService.createOrder(session, us);
        return "redirect:/bucket";
    }
}
