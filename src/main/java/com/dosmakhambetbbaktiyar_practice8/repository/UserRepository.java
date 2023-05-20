package com.dosmakhambetbbaktiyar_practice8.repository;

import com.dosmakhambetbbaktiyar_practice8.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
