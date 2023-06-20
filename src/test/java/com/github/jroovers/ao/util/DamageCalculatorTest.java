package com.github.jroovers.ao.util;

import com.github.jroovers.ao.model.entities.Weapon;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.github.jroovers.ao.TestDataFactory.getPerenniumBeamerQl200;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DamageCalculatorTest {

    @Test
    void testDamageRangeCalcFixed() {
        Weapon fixedWeapon = Weapon.builder()
                .name("100 damage")
                .ql(200)
                .minDamage(100)
                .maxDamage(100)
                .critical(100)
                .build();
        var noAc = DamageCalculator.damageRange(fixedWeapon, 1000, 0, 0);
        assertEquals(350, noAc.getMinimumDamage());
        assertEquals(350, noAc.getMaximumDamage());
        assertEquals(700, noAc.getCriticalDamage());

        var withAc = DamageCalculator.damageRange(fixedWeapon, 1000, 0, 5000);
        assertEquals(350, withAc.getMinimumDamage());
        assertEquals(350, withAc.getMaximumDamage());
        assertEquals(700, withAc.getCriticalDamage());

        var withAcAndAddDamage = DamageCalculator.damageRange(fixedWeapon, 1000, 100, 5000);
        assertEquals(450, withAcAndAddDamage.getMinimumDamage());
        assertEquals(450, withAcAndAddDamage.getMaximumDamage());
        assertEquals(800, withAcAndAddDamage.getCriticalDamage());

    }

    @Test
    void testDamageRangeCalcBeamer() {
        Weapon beamer = getPerenniumBeamerQl200();

        // 1000 = 3,5x weapon damage
        var test1 = DamageCalculator.damageRange(beamer, 1000, 0, 0);
        assertEquals(612, test1.getMinimumDamage());
        assertEquals(1487, test1.getMaximumDamage());
        assertEquals(2012, test1.getCriticalDamage());

        // 900 = 3,25x weapon damage
        var test2 = DamageCalculator.damageRange(beamer, 900, 0, 0);
        assertEquals(568, test2.getMinimumDamage());
        assertEquals(1381, test2.getMaximumDamage());

        // 1500 = 3,92~x weapon damage
        var test3 = DamageCalculator.damageRange(beamer, 1500, 0, 0);
        assertEquals(685, test3.getMinimumDamage());
        assertEquals(1664, test3.getMaximumDamage());
        assertEquals(2252, test3.getCriticalDamage());

        // Beyond MBS;
        var test4 = DamageCalculator.damageRange(beamer, 1750, 0, 0);
        assertThat(test3).isEqualTo(test4);

        // add flat damage
        var test5 = DamageCalculator.damageRange(beamer, 1500, 100, 0);
        assertEquals(685 + 100, test5.getMinimumDamage());
        assertEquals(1664 + 100, test5.getMaximumDamage());
        assertEquals(2252 + 100, test5.getCriticalDamage());

        // AC 1
        var test6 = DamageCalculator.damageRange(beamer, 1500, 100, 5000);
        assertEquals(685 + 100, test6.getMinimumDamage());
        assertEquals(1164 + 100, test6.getMaximumDamage());
        assertEquals(1752 + 100, test6.getCriticalDamage());

        // AC 2
        var test7 = DamageCalculator.damageRange(beamer, 1500, 100, 50000);
        assertEquals(685 + 100, test7.getMinimumDamage());
        assertEquals(685 + 100, test7.getMaximumDamage());
        assertEquals(1273 + 100, test7.getCriticalDamage());
    }


    @Test
    void getDamageMultiplier() {
        Weapon beamer = getPerenniumBeamerQl200();
        // 1k cap = 3.5
        BigDecimal multiplier = DamageCalculator.getDamageMultiplier(beamer, 1000);
        assertThat(multiplier).isEqualTo(BigDecimal.valueOf(3.500).setScale(3, RoundingMode.HALF_UP));

        // 1.3k  = 3.75
        multiplier = DamageCalculator.getDamageMultiplier(beamer, 1300);
        assertThat(multiplier).isEqualTo(BigDecimal.valueOf(3.750).setScale(3, RoundingMode.HALF_UP));

        // 3000 but capped by 1500 MBS = 3.917
        multiplier = DamageCalculator.getDamageMultiplier(beamer, 3000);
        assertThat(multiplier).isEqualTo(BigDecimal.valueOf(3.917).setScale(3, RoundingMode.HALF_UP));
    }

    @Test
    void someOtherWeapons() {
        Weapon maxMaFist = Weapon.builder()
                .name("3000 AR MA fist")
                .ql(500)
                .minDamage(450)
                .maxDamage(1300)
                .critical(800)
                .build();
        var range1 = DamageCalculator.damageRange(maxMaFist, 3000, 0, 10000);
        assertThat(range1.getMinimumDamage()).isEqualTo(2325);
        assertThat(range1.getMaximumDamage()).isEqualTo(5717);
        assertThat(range1.getCriticalDamage()).isEqualTo(9850);

        Weapon obsidianDescrator = Weapon.builder()
                .name("Obsidian Desecrator")
                .ql(1)
                .minDamage(425)
                .maxDamage(575)
                .critical(666)
                .build();
        var range2 = DamageCalculator.damageRange(obsidianDescrator, 3000, 0, 10000);
        assertThat(range2.getMinimumDamage()).isEqualTo(2195);
        assertThat(range2.getMaximumDamage()).isEqualTo(2195);
        assertThat(range2.getCriticalDamage()).isEqualTo(5637);

    }

    @Test
    void simulate() {
        Weapon obsidianDescrator = Weapon.builder()
                .name("Obsidian Desecrator")
                .ql(1)
                .minDamage(425)
                .maxDamage(575)
                .critical(666)
                .build();

        var results = DamageCalculator.simulate(obsidianDescrator, 3000, 0, 34);
        System.out.println(results);
    }

}