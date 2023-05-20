package com.dosmakhambetbbaktiyar_practice8.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.EVENTS_WRITE,Permission.EVENTS_READ,
            Permission.FILES_WRITE,Permission.FILES_READ,
            Permission.USERS_WRITE,Permission.USERS_READ)),
    MODERATOR(Set.of(Permission.EVENTS_WRITE,Permission.EVENTS_READ,
            Permission.FILES_WRITE,Permission.FILES_READ,
            Permission.USERS_READALL,Permission.USERS_READ)),
    USER(Set.of(Permission.EVENTS_READ,
            Permission.FILES_READ,
            Permission.USERS_READ));


    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities(){
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
