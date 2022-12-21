package com.github.faustofjunqueira.quarkuscamel.core.port.repository;

import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    Task create(TaskCreateDto dto, UUID ownerId);

    void delete(UUID taskId);

    Optional<Task> complete(UUID taskId);

    Collection<Task> list(TaskFilterDto filter);

    Optional<Task> getById(UUID taskId);
}
