package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class TaskCreateRequest {
    private String title;
    private String description;
}
