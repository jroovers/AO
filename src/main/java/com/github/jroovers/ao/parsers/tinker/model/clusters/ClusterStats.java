package com.github.jroovers.ao.parsers.tinker.model.clusters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class ClusterStats {
    private int impslot;
    private int loval;
    private double jobemod;
    private int hival;

    public ImplantSlot getImplantSlot() {
        return ImplantSlot.byInternalSlot(impslot);
    }

    public static ImplantSlot getImplantSlot(ClusterStats stats) {
        return ImplantSlot.byInternalSlot(stats.impslot);
    }
}
