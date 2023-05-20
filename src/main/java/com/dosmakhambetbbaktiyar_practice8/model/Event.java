package com.dosmakhambetbbaktiyar_practice8.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
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

    public Event() {
    }

    public Event(Long id, Status status, File file, User user) {
        this.id = id;
        this.status = status;
        this.file = file;
        this.user = user;
    }
}
