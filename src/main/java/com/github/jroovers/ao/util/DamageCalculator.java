package com.github.jroovers.ao.util;

import com.github.jroovers.ao.model.domain.DamageRange;
import com.github.jroovers.ao.model.domain.DamageSimulationResults;
import com.github.jroovers.ao.model.entities.Weapon;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.function.Function;

import static java.math.RoundingMode.HALF_UP;

@UtilityClass
public class DamageCalculator {

    private static final BigDecimal softCap = BigDecimal.valueOf(1000);
    private static final BigDecimal lowDivider = BigDecimal.valueOf(400);
    private static final BigDecimal highDivider = BigDecimal.valueOf(1200);


    public static DamageSimulationResults simulate(Weapon weapon, int attackRating, int inits, int critChance) {
        DamageRange range = damageRange(weapon, attackRating, 0, 0);
        int avgDamage = range.getAverageDamage(critChance);
        return DamageSimulationResults.builder()
                .duration(60)
                .hits(30)
                .criticalHits((30 * critChance) / 100)
                .averageDamagePerHit(avgDamage)
                .simulatedDamage(avgDamage * 30)
                .damageRange(range)
                .build();
    }

    public static BigDecimal getDamageMultiplier(Weapon weapon, int attackRating) {
        int effectiveSkill;
        if (weapon.getMaxBeneficialSkill() != null)
            effectiveSkill = attackRating <= weapon.getMaxBeneficialSkill() ? attackRating : weapon.getMaxBeneficialSkill();
        else
            effectiveSkill = attackRating;

        BigDecimal skill = BigDecimal.valueOf(effectiveSkill);
        BigDecimal base = BigDecimal.valueOf(1);
        BigDecimal multiplier = skill.min(softCap).divide(lowDivider, 3, HALF_UP);
        if (attackRating > 1000) {
            BigDecimal extra = skill.subtract(softCap).divide(highDivider, 3, HALF_UP);
            multiplier = multiplier.add(extra);
        }
        return multiplier.setScale(3, HALF_UP).add(base);
    }

    public static DamageRange damageRange(Weapon weapon, Integer attackRating, Integer addDamage, Integer armorClass) {
        int minDamage = getWeaponDamage(weapon, Weapon::getMinDamage, attackRating, addDamage, 0);
        int maxDamage = getWeaponDamage(weapon, Weapon::getMaxDamage, attackRating, addDamage, armorClass);
        int critMinDamage = getWeaponDamage(weapon, w -> w.getMinDamage() + w.getCritical(), attackRating, addDamage, 0);
        int critMaxDamage = getWeaponDamage(weapon, w -> w.getMaxDamage() + w.getCritical(), attackRating, addDamage, armorClass);
        return new DamageRange(minDamage, Math.max(minDamage, maxDamage), Math.max(critMinDamage, critMaxDamage));
    }

    private static int getWeaponDamage(Weapon weapon, Function<Weapon, Integer> damageGetter, int attackRating, int addDamage, int armorClass) {
        BigDecimal multiplier = getDamageMultiplier(weapon, attackRating);
        Integer weaponDamageStat = damageGetter.apply(weapon);
        int dmg = (int) (weaponDamageStat * multiplier.doubleValue());
        if (armorClass > 0) {
            dmg = dmg - (armorClass / 10);
            if (dmg < 0) dmg = 1;
        }
        return Math.min(dmg + addDamage, 13_000);
    }

}
