package com.dosmakhambetbbaktiyar_practice8.service;

import com.dosmakhambetbbaktiyar_practice8.model.Event;

import java.util.List;

public interface EventService extends GenericService<Event, Long> {
    List<Event> findByUser_UserName(String username);
}
