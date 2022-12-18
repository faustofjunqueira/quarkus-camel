package com.github.faustofjunqueira.quarkuscamel.core.port.repository;

import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;

import java.util.Collection;

public interface TaskRepository {

    Task create(TaskCreateDto dto, String ownerId);

    Task delete(String taskId);

    Task complete(String taskId);

    Collection<Task> list(TaskFilterDto filter);

}
