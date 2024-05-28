package com.example.SteamClone.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Bucket
{
    @Getter
    @Setter
    private List<Game> lst = new ArrayList<>();

    public int getSize()
    {
        return  lst.size();
    }
}
