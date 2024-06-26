package com.example.SteamClone.services;

import com.example.SteamClone.models.User;
import com.example.SteamClone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            User user =  userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("this user doesn't exist"));
            return user;
    }
}
