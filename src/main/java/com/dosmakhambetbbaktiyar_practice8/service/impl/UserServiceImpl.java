package com.dosmakhambetbbaktiyar_practice8.service.impl;

import com.dosmakhambetbbaktiyar_practice8.config.SecurityConfig;
import com.dosmakhambetbbaktiyar_practice8.model.User;
import com.dosmakhambetbbaktiyar_practice8.repository.UserRepository;
import com.dosmakhambetbbaktiyar_practice8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final SecurityConfig securityConfig;
    @Autowired
    public UserServiceImpl(UserRepository repository, SecurityConfig securityConfig) {
        this.repository = repository;
        this.securityConfig = securityConfig;
    }

    public User findById(Long id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return repository.findByUserName(username);
    }
}
