package com.dosmakhambetbbaktiyar_practice8.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @OneToOne
    private File file;
    @ManyToOne
    private User user;
}
