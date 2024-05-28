package com.example.SteamClone.services;

import com.example.SteamClone.models.User;
import com.example.SteamClone.models.enums.Role;
import com.example.SteamClone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user, boolean seller)
    {
        String email = user.getEmail();
        if (userRepository.findByEmail(email).isPresent())
        {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (seller)
        {
            user.getRoles().add(Role.ROLE_SELLER);
        }
        user.getRoles().add(Role.ROLE_BUYER);
        user.setBalance(0);
        log.info("Saving new User with email: {}, role: {}", email, user.getRoles());
        userRepository.save(user);
        return true;
    }

    public List<User> list()
    {
        return userRepository.findAll();
    }

    public void banUser(Long id)
    {
        User user = userRepository.findById(id).orElse(null);
        if (user != null)
        {
            if (user.isActive())
            {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
            else
            {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form)
    {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet())
        {
            if (roles.contains(key))
            {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
    public void changeUserBalance(Long id, int balance)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("this user doesn't exist"));;
        user.setBalance(user.getBalance() + balance);
        userRepository.save(user);
    }
    public User getUserByPrincipal(Principal principal)
    {
        if (principal == null)
        {
            return new User();
        }
        return userRepository.findByEmail(principal.getName()).orElseThrow();
    }
}
