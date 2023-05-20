package com.dosmakhambetbbaktiyar_practice8.service;

import com.dosmakhambetbbaktiyar_practice8.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService extends GenericService<File, Long> {

    File uploadFile(MultipartFile file);

    List<File> findByUserName(String username);

}
