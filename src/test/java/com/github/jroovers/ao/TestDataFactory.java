package com.github.jroovers.ao;

import com.github.jroovers.ao.model.entities.Weapon;

public class TestDataFactory {

    public static Weapon getPerenniumBeamerQl200() {
        return Weapon.builder()
                .name("Perennium Beamer")
                .ql(200)
                .minDamage(175)
                .maxDamage(425)
                .critical(150)
                .maxBeneficialSkill(1500)
                .build();
    }

}
