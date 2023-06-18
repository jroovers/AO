package com.github.jroovers.ao.parsers.tinker.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class DataModelWrapper {
    private DataModel data;
}
