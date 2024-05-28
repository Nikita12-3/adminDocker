package com.example.SteamClone.services;

import com.example.SteamClone.models.Game;
import com.example.SteamClone.models.User;
import com.example.SteamClone.repositories.GameRepository;
import com.example.SteamClone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SellerService
{
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    public List<Game> myListGames(User user, String title)
    {
        if (title != null)
        {
            return gameRepository.findByUserAndTitle(user, title);
        }
        return gameRepository.findByUser(user);
    }
}
