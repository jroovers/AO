package com.github.jroovers.ao.util;

import com.github.jroovers.ao.model.domain.DamageSimulationResults;
import com.github.jroovers.ao.model.entities.Weapon;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.function.Function;

import static java.math.RoundingMode.HALF_UP;

@UtilityClass
public class DamageCalculator {

    private static final BigDecimal softCap = BigDecimal.valueOf(1000);
    private static final BigDecimal lowDivider = BigDecimal.valueOf(400);
    private static final BigDecimal highDivider = BigDecimal.valueOf(1200);


    public static DamageSimulationResults simulate(Weapon weapon, int skill) {
        DamageSimulationResults results = new DamageSimulationResults();
        return null;
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

    private int getWeaponDamage(Weapon weapon, Function<Weapon, Integer> damageGetter, int attackRating, int addDamage, int armorClass) {
        BigDecimal multiplier = getDamageMultiplier(weapon, attackRating);
        Integer weaponDamageStat = damageGetter.apply(weapon);
        int dmg = (int) (weaponDamageStat * multiplier.doubleValue());
        if (armorClass > 0) {
            dmg = dmg - (armorClass / 10);
            if (dmg < 0) dmg = 1;
        }
        return dmg + addDamage;
    }

    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    public class DamageRange {
        private int minimumDamage;
        private int maximumDamage;
        private int criticalDamage;
    }

}
