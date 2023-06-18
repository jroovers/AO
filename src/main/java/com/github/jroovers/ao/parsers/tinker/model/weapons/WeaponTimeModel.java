package com.github.jroovers.ao.parsers.tinker.model.weapons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class WeaponTimeModel {
    private int attack;
    private int recharge;
}
