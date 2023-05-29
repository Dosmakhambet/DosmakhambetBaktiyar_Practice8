package com.dosmakhambetbbaktiyar_practice8.controller;

import com.dosmakhambetbbaktiyar_practice8.dto.FileDto;
import com.dosmakhambetbbaktiyar_practice8.model.File;
import com.dosmakhambetbbaktiyar_practice8.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/api/V1/files")
public class FileController {
    private final FileService service;

    @Autowired
    public FileController(FileService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('files:write')")
    public ResponseEntity<FileDto> findById(@PathVariable("id") Long id){

        return new ResponseEntity<>(FileDto.asDTO(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('files:write')")
    public ResponseEntity<List<FileDto>> findAll(){
        return new ResponseEntity<>(service.findAll().stream().map(FileDto::asDTO).toList(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('files:write,files:read')")
    public ResponseEntity<FileDto> save(@RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(FileDto.asDTO(service.uploadFile(file)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('files:write')")
    public ResponseEntity<FileDto> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAnyAuthority('files:read')")
    public ResponseEntity<List<FileDto>> findByUserName(){

        return new ResponseEntity<>(service.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).stream().map(FileDto::asDTO).toList(), HttpStatus.OK);
    }
}
