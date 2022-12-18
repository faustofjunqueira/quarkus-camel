package com.github.faustofjunqueira.quarkuscamel.core.service;

import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.repository.TaskRepository;
import com.github.faustofjunqueira.quarkuscamel.core.port.service.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@RequiredArgsConstructor
@ApplicationScoped
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public Collection<Task> list(@NonNull TaskFilterDto filter) {
        return repository.list(filter);
    }

    public Task create(@NonNull TaskCreateDto dto,@NonNull String ownerId) {
        return repository.create(dto, ownerId);
    }

    public Task delete(@NonNull String taskId) {
        return repository.delete(taskId);
    }

    public Task complete(@NonNull String taskId) {
        return repository.complete(taskId);
    }
}
