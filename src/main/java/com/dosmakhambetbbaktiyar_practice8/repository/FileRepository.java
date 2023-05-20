package com.dosmakhambetbbaktiyar_practice8.repository;

import com.dosmakhambetbbaktiyar_practice8.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    @Query("SELECT f From File f INNER JOIN Event e ON e.file.id = f.id INNER JOIN User u ON e.user.id = u.id WHERE u.userName = :username")
    List<File> findByUserName(@Param("username") String username);
}
