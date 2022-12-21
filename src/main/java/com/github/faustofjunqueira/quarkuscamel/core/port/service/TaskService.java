package com.github.faustofjunqueira.quarkuscamel.core.port.service;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request.TaskCreateRequest;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;

import java.util.Collection;
import java.util.UUID;

public interface TaskService {

    Collection<Task> list(TaskFilterDto filter);

    Task create(TaskCreateDto dto, UUID ownerId);

    void delete(UUID taskId);

    void complete(UUID taskId);

    Task getById(UUID taskId);
}
