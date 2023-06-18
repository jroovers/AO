package com.github.jroovers.ao.parsers.tinker.model;

import com.github.jroovers.ao.parsers.tinker.model.bosses.BossModel;
import com.github.jroovers.ao.parsers.tinker.model.clusters.ClusterModel;
import com.github.jroovers.ao.parsers.tinker.model.implants.ImplantModel;
import com.github.jroovers.ao.parsers.tinker.model.nanos.NanoModel;
import com.github.jroovers.ao.parsers.tinker.model.symbiants.SymbiantModel;
import com.github.jroovers.ao.parsers.tinker.model.weapons.WeaponModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Jacksonized
@Builder
@Getter
@Setter
public class DataModel {
    private Map<String, ClusterModel> clusters;
    private Map<Integer, ImplantModel> implants;
    private Map<Integer, NanoModel> nanos;
    private Map<Integer, WeaponModel> weapons;
    private Map<Integer, SymbiantModel> symbiants;
    private Map<String, BossModel> bosses;

}
