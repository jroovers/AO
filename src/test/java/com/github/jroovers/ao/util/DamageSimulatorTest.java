package com.github.jroovers.ao.util;

import com.github.jroovers.ao.model.entities.Weapon;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageSimulatorTest {

    @Test
    void testDamageRangeCalcFixed(){
        Weapon beamer = Weapon.builder()
                .name("Perennium Beamer")
                .ql(200)
                .minDamage(100)
                .maxDamage(100)
                .critical(100)
                .build();
        var noAc = DamageSimulator.damageRange(beamer, 1000, 0);
        assertEquals(350, noAc.getMinimumDamage());
        assertEquals(350, noAc.getMaximumDamage());
        assertEquals(700, noAc.getCritDamage());

        var withAc = DamageSimulator.damageRange(beamer, 1000, 5000);
        assertEquals(350, withAc.getMinimumDamage());
        assertEquals(350, withAc.getMaximumDamage());
        assertEquals(700, withAc.getCritDamage());

    }

    @Test
    void testDamageRangeCalcBeamer(){
        Weapon beamer = Weapon.builder()
                .name("Perennium Beamer")
                .ql(200)
                .minDamage(175)
                .maxDamage(425)
                .critical(150)
                .build();
        var test1 = DamageSimulator.damageRange(beamer, 1000, 0);
        assertEquals(612, test1.getMinimumDamage());
        assertEquals(1487, test1.getMaximumDamage());
        assertEquals(2012, test1.getCritDamage());


        var test2 = DamageSimulator.damageRange(beamer, 900, 0);
        Assertions.assertThat(569).isCloseTo(test2.getMinimumDamage(), Offset.offset(1));
        Assertions.assertThat(1381).isCloseTo(test2.getMaximumDamage(), Offset.offset(1));
    }




}