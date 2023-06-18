package com.github.jroovers.ao.parsers.tinker.model.weapons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class WeaponDamageModel {
    private int minimum;
    private int maximum;
    private int critical;
    private int type;
}
