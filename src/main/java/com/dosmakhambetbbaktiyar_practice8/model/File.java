package com.dosmakhambetbbaktiyar_practice8.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import jakarta.persistence.*;


@Data
@Builder
@Setter
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String location;
    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    private Status status;

    public File(){
    }

    public File(Long id, String location, Status status) {
        this.id = id;
        this.location = location;
        this.status = status;
    }
}
