package com.dosmakhambetbbaktiyar_practice8.service.impl;

import com.dosmakhambetbbaktiyar_practice8.model.Event;
import com.dosmakhambetbbaktiyar_practice8.model.File;
import com.dosmakhambetbbaktiyar_practice8.model.Status;
import com.dosmakhambetbbaktiyar_practice8.model.User;
import com.dosmakhambetbbaktiyar_practice8.repository.FileRepository;
import com.dosmakhambetbbaktiyar_practice8.service.AmazonService;
import com.dosmakhambetbbaktiyar_practice8.service.EventService;
import com.dosmakhambetbbaktiyar_practice8.service.FileService;
import com.dosmakhambetbbaktiyar_practice8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository repository;
    private final UserService userService;
    private final EventService eventService;
    private final AmazonService amazonService;

    @Autowired
    public FileServiceImpl(FileRepository repository, UserService userService, EventService eventService, AmazonService amazonService) {
        this.repository = repository;
        this.userService = userService;
        this.eventService = eventService;
        this.amazonService = amazonService;
    }


    @Override
    public File findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<File> findAll() {
        return repository.findAll();
    }

    @Override
    public File save(File file) {
        return repository.save(file);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public File uploadFile(MultipartFile file) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        String location = amazonService.uploadFile(file);
        File uploadedFile = repository.save(File.builder()
                    .status(Status.ACTIVE)
                    .location(location)
                    .build()
        );
        eventService.save(Event.builder()
                .file(uploadedFile)
                .user(user)
                .status(Status.ACTIVE)
                .build()
        );

        return uploadedFile;
    }

    @Override
    public List<File> findByUserName(String username) {
        return repository.findByUserName(username);
    }
}
