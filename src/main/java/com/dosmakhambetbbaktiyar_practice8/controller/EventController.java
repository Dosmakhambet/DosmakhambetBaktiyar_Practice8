package com.dosmakhambetbbaktiyar_practice8.controller;

import com.dosmakhambetbbaktiyar_practice8.dto.EventDto;
import com.dosmakhambetbbaktiyar_practice8.model.Event;
import com.dosmakhambetbbaktiyar_practice8.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/V1/events")
public class EventController {
    private final EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('events:write')")
    public ResponseEntity<EventDto> findById(@PathVariable("id") Long id){

        return new ResponseEntity<>(EventDto.asDTO(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('events:write')")
    public ResponseEntity<List<EventDto>> findAll(){

        return new ResponseEntity<>(service.findAll().stream().map(EventDto::asDTO).toList(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('events:write')")
    public ResponseEntity<EventDto> save(@RequestBody Event event){

        return new ResponseEntity<>(EventDto.asDTO(service.save(event)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('events:write')")
    public ResponseEntity<EventDto> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAnyAuthority('events:read')")
    public ResponseEntity<List<EventDto>> findByUser_UserName(){
        return new ResponseEntity<>(service.findByUser_UserName(SecurityContextHolder.getContext().getAuthentication().getName()).stream().map(EventDto::asDTO).toList(), HttpStatus.OK);
    }
}
