package com.dosmakhambetbbaktiyar_practice8.service;

import com.dosmakhambetbbaktiyar_practice8.model.User;

import java.util.Optional;

public interface UserService extends GenericService<User, Long>{
    Optional<User> findByUserName(String username);
}
