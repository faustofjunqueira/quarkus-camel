package com.github.faustofjunqueira.quarkuscamel.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TaskCreateDto {
    private String title;
    private String description;
}
