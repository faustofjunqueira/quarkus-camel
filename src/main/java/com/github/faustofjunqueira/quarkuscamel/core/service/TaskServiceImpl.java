package com.github.faustofjunqueira.quarkuscamel.core.service;

import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.Page;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.error.ModelNotFoundException;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.repository.TaskRepository;
import com.github.faustofjunqueira.quarkuscamel.core.port.service.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@ApplicationScoped
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public Page<Task> list(@NonNull TaskFilterDto filter) {
        return repository.list(filter);
    }

    public Task create(@NonNull TaskCreateDto dto,@NonNull UUID ownerId) {
        return repository.create(dto, ownerId);
    }

    public void delete(@NonNull UUID taskId) {
        repository.delete(taskId);
    }

    public void complete(@NonNull UUID taskId) {
        repository.complete(taskId)
                .orElseThrow(() -> {
                    throw new ModelNotFoundException("Task", taskId);
                });
    }

    @Override
    public Task getById(UUID taskId) {
        return repository.getById(taskId)
                .orElseThrow(() -> {
                    throw new ModelNotFoundException("Task", taskId);
                });
    }
}
