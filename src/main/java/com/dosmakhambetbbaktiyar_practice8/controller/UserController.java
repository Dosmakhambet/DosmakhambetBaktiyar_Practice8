package com.dosmakhambetbbaktiyar_practice8.controller;

import com.dosmakhambetbbaktiyar_practice8.dto.UserDto;
import com.dosmakhambetbbaktiyar_practice8.model.User;
import com.dosmakhambetbbaktiyar_practice8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/V1/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write,users:readall')")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id){

        return new ResponseEntity<>(UserDto.asDTO(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('users:write,users:readall')")
    public ResponseEntity<List<UserDto>> findAll(){

        return new ResponseEntity<>(service.findAll().stream().map(UserDto::asDTO).toList(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public ResponseEntity<UserDto> save(@RequestBody User user){

        return new ResponseEntity<>(UserDto.asDTO(service.save(user)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public ResponseEntity<UserDto> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAnyAuthority('users:read')")
    public ResponseEntity<UserDto> findByUserName(){

        return new ResponseEntity<>(UserDto.asDTO(service.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).get()), HttpStatus.OK);
    }

}
