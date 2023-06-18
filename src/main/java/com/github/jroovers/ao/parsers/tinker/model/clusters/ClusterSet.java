package com.github.jroovers.ao.parsers.tinker.model.clusters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class ClusterSet {
    @JsonProperty(value = "Faded")
    private ClusterStats faded;
    @JsonProperty(value = "Bright")
    private ClusterStats bright;
    @JsonProperty(value = "Shiny")
    private ClusterStats shiny;

}
