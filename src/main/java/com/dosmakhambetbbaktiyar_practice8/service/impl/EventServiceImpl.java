package com.dosmakhambetbbaktiyar_practice8.service.impl;

import com.dosmakhambetbbaktiyar_practice8.model.Event;
import com.dosmakhambetbbaktiyar_practice8.repository.EventRepository;
import com.dosmakhambetbbaktiyar_practice8.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Event save(Event event) {
        return repository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Event> findByUser_UserName(String username) {
        return repository.findByUser_UserName(username);
    }
}
