package com.dosmakhambetbbaktiyar_practice8.dto;

import com.dosmakhambetbbaktiyar_practice8.model.Event;
import com.dosmakhambetbbaktiyar_practice8.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private Status status;
    private FileDto file;
    private UserDto user;

    public static EventDto asDTO(Event event){
        return EventDto.builder()
                .id(event.getId())
                .status(event.getStatus())
                .file(FileDto.asDTO(event.getFile()))
                .user(UserDto.asDTO(event.getUser()))
                .build();
    }

    public Event asEntity(){
        return Event.builder()
                .id(id)
                .status(status)
                .file(file.asEntity())
                .user(user.asEntity())
                .build();
    }
}

