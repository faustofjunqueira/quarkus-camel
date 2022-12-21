package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.response;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private UUID id;
    private UUID ownerId;
    private String title;
    private String description;
    private ZonedDateTime completedAt;
    private ZonedDateTime createdAt;
    private ZonedDateTime deletedAt;
}
