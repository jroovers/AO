package com.github.jroovers.ao.parsers.tinker.model.clusters;

import java.util.Arrays;

public enum ImplantSlot {
    EYE(1),
    HEAD(2),
    EAR(3),
    RIGHTARM(4),
    CHEST(5),
    LEFTARM(6),
    RIGHTWRIST(7),
    WAIST(8),
    LEFTWRIST(9),
    RIGHTHAND(10),
    LEGS(11),
    LEFTHAND(12),
    FEET(13);

    private final int slot;

    ImplantSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public static ImplantSlot byInternalSlot(int slot) {
        return Arrays.stream(ImplantSlot.values())
                .filter(imp -> imp.getSlot() == slot)
                .findFirst().orElse(null);
    }
}
