package com.github.jroovers.ao.model.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DamageSimulationResults {

    private Integer duration;
    private Integer simulatedDamage;

    private Integer minimumHit;
    private Integer maximumHit;
    private Integer criticalHit;

    private Integer hits;
    private Integer criticalHits;

}
