package com.github.jroovers.ao.parsers.tinker.model.weapons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Map;

@Jacksonized
@Builder
@Getter
@Setter
public class WeaponModel {
    private String name;
    private int ql;
    private WeaponTimeModel times;
    private WeaponDamageModel damage;
    @JsonProperty(value = "clipsize")
    private int clipSize;
    private List<String> props;
    private Map<String, Object> reqs;
    @JsonProperty(value = "attack_skills")
    private Map<String, Integer> attackSkills;
    private Map<String, Integer> other;

    @Override
    public String toString() {
        return "WeaponModel{" +
                "name='" + name + '\'' +
                ", ql=" + ql +
                '}';
    }
}
