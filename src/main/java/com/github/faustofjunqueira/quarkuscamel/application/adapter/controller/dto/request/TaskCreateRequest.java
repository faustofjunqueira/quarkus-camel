package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
public class TaskCreateRequest {
    public String title;
    public String description;
}
