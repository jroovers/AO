package com.github.jroovers.ao.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Weapon {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private Long aoid;

    private String name;

    private Integer ql;

    private Integer minDamage;
    private Integer maxDamage;
    private Integer critical;

    private Integer clipSize;

    private Double attackSpeed;
    private Double rechargeSpeed;
    private Double equipSpeed;

    public Double getReloadSpeed() {
        return this.rechargeSpeed;
    }

    public String getSelf_() {
        return "/weapon/" + aoid;
    }
}
