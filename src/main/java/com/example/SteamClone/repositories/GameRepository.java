package com.example.SteamClone.repositories;

import com.example.SteamClone.models.Game;
import com.example.SteamClone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByActive(boolean active);

    List<Game> findByTitleAndActive(String title, boolean active);

    List<Game> findByUser(User user);
    List<Game> findByUserAndTitle(User user, String title);
}
