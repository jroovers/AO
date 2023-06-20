package com.github.jroovers.ao.model.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class DamageRange {
    private int minimumDamage;
    private int maximumDamage;
    private int criticalDamage;

    public int getAverageDamage(int critChance) {
        int normalChance = critChance >= 100 ? 0 : 100 - critChance;
        return (normalChance * ((minimumDamage + maximumDamage) / 2) + critChance * criticalDamage) / 100;
    }
}