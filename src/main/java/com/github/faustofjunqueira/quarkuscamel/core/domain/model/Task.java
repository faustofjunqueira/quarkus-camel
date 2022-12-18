package com.github.faustofjunqueira.quarkuscamel.core.domain.model;

import lombok.Value;

import java.time.ZonedDateTime;
import java.util.UUID;

@Value
public class Task {
    UUID id;
    UUID ownerId;
    String title;
    String description;
    ZonedDateTime completedAt;
    ZonedDateTime createdAt;
    ZonedDateTime deletedAt;
}
