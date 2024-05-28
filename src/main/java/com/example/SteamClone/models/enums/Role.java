package com.example.SteamClone.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_BUYER, ROLE_SELLER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
