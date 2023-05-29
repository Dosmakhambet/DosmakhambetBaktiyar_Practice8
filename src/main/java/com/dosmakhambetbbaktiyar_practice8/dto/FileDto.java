package com.dosmakhambetbbaktiyar_practice8.dto;

import com.dosmakhambetbbaktiyar_practice8.model.File;
import com.dosmakhambetbbaktiyar_practice8.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private Long id;
    private String location;
    private Status status;


    public static FileDto asDTO(File file){
        return new FileDto(file.getId(),file.getLocation(),file.getStatus());
    }

    public File asEntity(){
        return new File(id,location,status);
    }
}
