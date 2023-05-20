package com.dosmakhambetbbaktiyar_practice8.repository;

import com.dosmakhambetbbaktiyar_practice8.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUser_UserName(String username);
}
