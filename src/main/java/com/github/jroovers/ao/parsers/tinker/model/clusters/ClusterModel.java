package com.github.jroovers.ao.parsers.tinker.model.clusters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class ClusterModel {
    private ClusterSet normal;
    private ClusterSet refined;
    private Double npmod;
    private String jobeskill;

}
