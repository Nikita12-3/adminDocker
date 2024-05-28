package com.example.SteamClone.controllers;

import com.example.SteamClone.models.Game;
import com.example.SteamClone.models.User;
import com.example.SteamClone.services.GameService;
import com.example.SteamClone.services.SellerService;
import com.example.SteamClone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_SELLER')")
public class SellerController
{
    private final SellerService sellerService;
    private final GameService gameService;
    private final UserService userService;
    @GetMapping("/seller")
    public String seller(@RequestParam(name = "title", required = false) String title, Principal principal, Model model)
    {
        model.addAttribute("games", sellerService.myListGames(userService.getUserByPrincipal(principal), title));
        return "seller";
    }

    @GetMapping("/seller-stat")
    public String sellerStat()
    {
        return "seller-stat";
    }


    @GetMapping("/seller-addGame")
    public String sellerAdd()
    {
        return "seller-addGame";
    }
    @GetMapping("/seller-editGame/{id}")
    public String sellerEdit(@PathVariable Long id, Model model)
    {
        Game game = gameService.getGameById(id);
        model.addAttribute("game", game);
        return "seller-editGame";
    }
}
