package com.github.faustofjunqueira.quarkuscamel.core.domain.enums;

import lombok.Getter;

@Getter
public enum EnumTaskStatus {
    CREATED("CREATED"),
    COMPLETED("COMPLETED"),
    DELETED("DELETED");

    private final String value;

    EnumTaskStatus(String value) {
        this.value = value;
    }

}
