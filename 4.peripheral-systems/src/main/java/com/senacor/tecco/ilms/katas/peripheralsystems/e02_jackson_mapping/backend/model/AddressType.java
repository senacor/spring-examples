package com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum AddressType {
    HOME,
    WORK,
    @JsonEnumDefaultValue UNKNOWN
}
