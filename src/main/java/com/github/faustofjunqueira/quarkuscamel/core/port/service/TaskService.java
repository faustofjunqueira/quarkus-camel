package com.github.faustofjunqueira.quarkuscamel.core.port.service;

import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;

import java.util.Collection;

public interface TaskService {

    Collection<Task> list(TaskFilterDto filter);

    Task create(TaskCreateDto dto, String ownerId);

    Task delete(String taskId);

    Task complete(String taskId);
}
