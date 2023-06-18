package com.github.jroovers.ao.parsers.tinker.model.clusters;

public enum ClusterSlot {
    FADED("Faded"),
    BRIGHT("Bright"),
    SHINY("Shiny");

    private final String slot;

    ClusterSlot(String slot) {
        this.slot = slot;
    }

    public String getSlot() {
        return slot;
    }
}
