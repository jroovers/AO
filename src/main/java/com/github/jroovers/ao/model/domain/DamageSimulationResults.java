package com.github.jroovers.ao.model.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class DamageSimulationResults {

    private Integer duration;
    private Integer simulatedDamage;

    private Integer averageDamagePerHit;
    private DamageRange damageRange;

    private Integer hits;
    private Integer criticalHits;

}
