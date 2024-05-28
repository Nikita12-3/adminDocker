package com.example.SteamClone.services;

import com.example.SteamClone.models.Game;
import com.example.SteamClone.models.Image;
import com.example.SteamClone.models.User;
import com.example.SteamClone.repositories.GameRepository;
import com.example.SteamClone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService
{
    private final GameRepository gameRepository;
    private final UserService userService;

    public List<Game> listGames(String title)
    {
        if (title != null)
        {
            return gameRepository.findByTitleAndActive(title, true);
        }
        return gameRepository.findByActive(true);
    }

    public void saveGame(Principal principal, Game game, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException
    {
        game.setUser(userService.getUserByPrincipal(principal));
        game.setRating(4);
        game.setActive(true);
        setImage(game, file1, file2, file3);
        Game gameFromDb = gameRepository.save(game);
        log.info("Saving new Game. Title: {}; Author email: {}", game.getTitle(), game.getUser().getEmail());
        Image temp = gameFromDb.getImages().get(0);//Сделать безопасно если не выбрал фотку
        if (temp.isPreviewImage())
        {
            gameFromDb.setPreviewImageId(temp.getId());
        }
        gameRepository.save(game);
    }
    public void updateGame(Game game, MultipartFile file1, MultipartFile file2, MultipartFile file3, Long id) throws IOException
    {
        Game temp = gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("this game doesn't exist"));;
        temp.setTitle(game.getTitle());
        temp.setDescription(game.getDescription());
        temp.setPrice(game.getPrice());
        setImage(temp, file1, file2, file3);
        gameRepository.save(temp);
    }

    private Image toImageEntity(MultipartFile file) throws IOException
    {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteGame(Game game)
    {
        game.setActive(!game.isActive());
        gameRepository.save(game);
    }

    public Game getGameById(Long id)
    {
        return gameRepository.findById(id).orElse(null);
    }

    private void setImage(Game game, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException
    {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0)
        {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            game.addImageToGame(image1);
        }
        if (file2.getSize() != 0)
        {
            image2 = toImageEntity(file2);
            game.addImageToGame(image2);
        }
        if (file3.getSize() != 0)
        {
            image3 = toImageEntity(file3);
            game.addImageToGame(image3);
        }
    }
}
