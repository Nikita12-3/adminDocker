package com.example.SteamClone.repositories;

import com.example.SteamClone.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
