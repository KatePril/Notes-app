package org.kate.notes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor()

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "text", columnDefinition = "text")
    private String text;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private LocalDateTime created;

    public enum Priority {
        ORDINARY,
        IMPORTANT,
        VITAL
    }

}
