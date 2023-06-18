package com.github.jroovers.ao.util;

import com.github.jroovers.ao.model.domain.DamageSimulationResults;
import com.github.jroovers.ao.model.entities.Weapon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DamageSimulator {

    public static DamageSimulationResults simulate(Weapon weapon, int skill) {
        DamageSimulationResults results = new DamageSimulationResults();
        return null;
    }


    public static DamageRange damageRange(Weapon weapon, Integer skill, Integer armorClass) {
        int minDamage = getDamage(weapon.getMinDamage(), skill, 0);
        int maxDamage = getDamage(weapon.getMaxDamage(), skill, armorClass);
        int critMinDamage = getDamage(weapon.getMinDamage() + weapon.getCritical(), skill, 0);
        int critMaxDamage = getDamage(weapon.getMaxDamage() + weapon.getCritical(), skill, armorClass);
        return new DamageRange(minDamage, Math.max(minDamage, maxDamage), Math.max(critMinDamage, critMaxDamage));
    }

    private int getDamage(int damage, int skill, int armorClass) {
        double floatDamage = damage * (1.0 + (double) skill / 400);
        int dmg = (int) floatDamage;
        if (armorClass > 0) {
            dmg = dmg - (armorClass / 10);
            if (dmg < 0) dmg = 1;
        }
        return dmg;
    }

    @AllArgsConstructor
    @Getter
    public class DamageRange {
        private int minimumDamage;
        private int maximumDamage;
        private int critDamage;
    }
}
