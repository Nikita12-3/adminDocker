package com.example.SteamClone.controllers;

import com.example.SteamClone.models.Bucket;
import com.example.SteamClone.models.Game;
import com.example.SteamClone.models.User;
import com.example.SteamClone.services.GameService;
import com.example.SteamClone.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final UserService userService;
    private Bucket bucket = new Bucket();

    @GetMapping("/")
    public String games(@RequestParam(name = "title", required = false) String title, Principal principal, Model model)
    {
        model.addAttribute("games", gameService.listGames(title));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("bucket", bucket);
        return "games";
    }

    @GetMapping("/game/{id}")
    public String gameInfo(@PathVariable Long id, Model model)
    {
        Game game = gameService.getGameById(id);
        model.addAttribute("game", game);
        model.addAttribute("images", game.getImages());
        return "game-info";
    }

    @PostMapping("/game/create")
    public String createGame(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Game game, Principal principal) throws IOException
    {
        gameService.saveGame(principal, game, file1, file2, file3);
        return "redirect:/seller";
    }
    @PostMapping("/game/edit/{id}")
    public String editGame(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, Game game, @PathVariable Long id) throws IOException
    {
        gameService.updateGame(game, file1, file2, file3, id);
        return "redirect:/seller";
    }
    @PostMapping("/game/delete/{id}")
    public String deleteGame(@PathVariable Long id)
    {
        Game game = gameService.getGameById(id);
        gameService.deleteGame(game);
        return "redirect:/seller";
    }

    @PostMapping("/game/addGame/{id}")
    public String addBucketGame(@PathVariable Long id, HttpSession session)
    {
        Game game = gameService.getGameById(id);
        bucket.getLst().add(game);
        List<Game> lst = (List<Game>)session.getAttribute("lstBucket");
        if (lst == null)
        {
            lst = new ArrayList<>();
        }
        lst.add(game);
        session.setAttribute("lstBucket", lst);
        return "redirect:/";
    }
}
