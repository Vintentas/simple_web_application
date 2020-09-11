package org.example.springtest.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int changerId;
    private LocalDateTime date;
    private String message;
    private String changes;
    private int changedUserId;
}

