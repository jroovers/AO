package com.github.jroovers.ao.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PocketBoss {

    @Id
    @GeneratedValue
    private Long id;

    private Long aoid;
}
