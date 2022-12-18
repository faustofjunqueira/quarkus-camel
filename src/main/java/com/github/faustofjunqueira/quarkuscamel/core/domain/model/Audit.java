package com.github.faustofjunqueira.quarkuscamel.core.domain.model;

import com.github.faustofjunqueira.quarkuscamel.core.domain.enums.EnumTaskStatus;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.UUID;

@Value
public class Audit {
    UUID id;
    UUID userId;
    UUID taskId;
    ZonedDateTime auditedAt;
    EnumTaskStatus beforeStatus;
    EnumTaskStatus afterStatus;

}
