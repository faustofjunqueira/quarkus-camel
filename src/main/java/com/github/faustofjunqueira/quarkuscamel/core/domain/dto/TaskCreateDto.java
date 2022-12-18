package com.github.faustofjunqueira.quarkuscamel.core.domain.dto;

import lombok.Data;

@Data
public class TaskCreateDto {
    private String title;
    private String description;
}
