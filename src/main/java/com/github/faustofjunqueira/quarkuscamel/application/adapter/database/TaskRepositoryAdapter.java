package com.github.faustofjunqueira.quarkuscamel.application.adapter.database;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.database.entity.TaskEntity;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@ApplicationScoped
public class TaskRepositoryAdapter implements TaskRepository {

    private final ModelMapper mapper;

    @Override
    public Task create(TaskCreateDto dto, UUID ownerId) {
        var taskEntity = TaskEntity.builder()
                .ownerId(ownerId)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
        taskEntity.persist();

        return mapper.map(taskEntity, Task.class);
    }

    @Override
    public void delete(UUID taskId) {
        TaskEntity.deleteById(taskId);
    }

    @Override
    public Optional<Task> complete(UUID taskId) {
        return TaskEntity.CompleteById(taskId).map(te -> mapper.map(te, Task.class));
    }

    @Override
    public Collection<Task> list(TaskFilterDto filter) {
        return null;
    }

    @Override
    public Optional<Task> getById(UUID taskId) {
        Optional<TaskEntity> entity = TaskEntity.findByIdOptional(taskId);
        return entity.map(te -> mapper.map(te, Task.class));
    }
}
