package com.github.jroovers.ao.converter;

import com.github.jroovers.ao.model.entities.Weapon;
import com.github.jroovers.ao.parsers.tinker.model.weapons.WeaponModel;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

import static com.github.jroovers.ao.parsers.tinker.util.TinkerUtils.*;

public class TinkerWeaponConverter implements Converter<Map.Entry<Integer, WeaponModel>, Weapon> {
    @Override
    public Weapon convert(Map.Entry<Integer, WeaponModel> source) {
        Long aoId = Long.valueOf(source.getKey());
        WeaponModel weapon = source.getValue();
        return Weapon.builder()
                .aoid(aoId)
                .ql(weapon.getQl())
                .name(weapon.getName())
                .minDamage(weapon.getDamage().getMinimum())
                .maxDamage(weapon.getDamage().getMaximum())
                .critical(weapon.getDamage().getCritical())
                .attackSpeed(timing(weapon.getTimes().getAttack()))
                .rechargeSpeed(timing(weapon.getTimes().getRecharge()))
                .clipSize(clipSize(weapon.getClipSize()))
                .maxBeneficialSkill(mbs(weapon.getOther()))
                .build();
    }
}
