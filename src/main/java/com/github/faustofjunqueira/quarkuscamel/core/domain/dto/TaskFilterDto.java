package com.github.faustofjunqueira.quarkuscamel.core.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskFilterDto extends Paging {
    private UUID userId;

}
