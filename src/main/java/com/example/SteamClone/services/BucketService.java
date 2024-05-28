package com.example.SteamClone.services;

import com.example.SteamClone.models.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketService
{
    public int sumBucket(List<Game> lst)
    {
        return lst.stream().mapToInt(Game::getPrice).sum();
    }
}
