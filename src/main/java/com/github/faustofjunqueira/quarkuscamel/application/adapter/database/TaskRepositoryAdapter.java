package com.github.faustofjunqueira.quarkuscamel.application.adapter.database;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.database.entity.TaskEntity;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.Page;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.repository.TaskRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ApplicationScoped
public class TaskRepositoryAdapter implements TaskRepository {

    private final static int MAX_PAGING_ELEMENT = 25;

    private final ModelMapper mapper;

    @Override
    public Task create(TaskCreateDto dto, UUID ownerId) {
        var taskEntity = TaskEntity.builder().ownerId(ownerId).title(dto.getTitle()).description(dto.getDescription()).build();
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
    public Page<Task> list(TaskFilterDto filter) {
        var offset = filter.getOffset() == null ? 0 : filter.getOffset();
        var limit = filter.getLimit() == null ? MAX_PAGING_ELEMENT : filter.getLimit();
        PanacheQuery<TaskEntity> taskFilteringQuery = filter.getUserId() != null ?
                TaskEntity.find("#Task.filter", Parameters.with("ownerId", filter.getUserId())) :
                TaskEntity.findAll();
        taskFilteringQuery.range(offset, offset + limit - 1);
        return Page.<Task>builder().total(taskFilteringQuery.count()).data(taskFilteringQuery.list().stream().map(te -> mapper.map(te, Task.class)).collect(Collectors.toList())).build();
    }

    @Override
    public Optional<Task> getById(UUID taskId) {
        Optional<TaskEntity> entity = TaskEntity.findByIdOptional(taskId);
        return entity.map(te -> mapper.map(te, Task.class));
    }
}
