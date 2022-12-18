package com.github.faustofjunqueira.quarkuscamel.application.adapter.panache;

import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@RequiredArgsConstructor
@ApplicationScoped
public class PanacheTaskRepository implements TaskRepository {
    @Override
    public Task create(TaskCreateDto dto, String ownerId) {
        return null;
    }

    @Override
    public Task delete(String taskId) {
        return null;
    }

    @Override
    public Task complete(String taskId) {
        return null;
    }

    @Override
    public Collection<Task> list(TaskFilterDto filter) {
        return null;
    }
}
