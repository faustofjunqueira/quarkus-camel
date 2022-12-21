package com.github.faustofjunqueira.quarkuscamel.core.domain.model;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private UUID id;
    private UUID ownerId;
    private String title;
    private String description;
    private ZonedDateTime completedAt;
    private ZonedDateTime createdAt;
    private ZonedDateTime deletedAt;
}
