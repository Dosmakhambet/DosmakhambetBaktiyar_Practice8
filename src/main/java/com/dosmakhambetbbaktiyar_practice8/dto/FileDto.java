package com.dosmakhambetbbaktiyar_practice8.dto;

import com.dosmakhambetbbaktiyar_practice8.model.File;
import com.dosmakhambetbbaktiyar_practice8.model.Status;
import lombok.Data;

@Data
public class FileDto {
    private Long id;
    private String location;
    private Status status;

    public FileDto(Long id, String location, Status status) {
        this.id = id;
        this.location = location;
        this.status = status;
    }

    public static FileDto asDTO(File file){
        return new FileDto(file.getId(),file.getLocation(),file.getStatus());
    }

    public File asEntity(){
        return new File(id,location,status);
    }
}
