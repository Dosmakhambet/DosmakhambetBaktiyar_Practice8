package com.dosmakhambetbbaktiyar_practice8.model;

import lombok.*;

import jakarta.persistence.*;


@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
