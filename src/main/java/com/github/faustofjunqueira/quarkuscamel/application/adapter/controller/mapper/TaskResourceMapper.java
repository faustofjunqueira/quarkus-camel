package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.mapper;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.TaskResource;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request.TaskCreateRequest;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.response.TaskResponse;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "cdi")
public interface TaskResourceMapper {
    TaskCreateDto map(TaskCreateRequest request);
    TaskResponse map(Task model);
    Collection<TaskResponse> map(Collection<Task> coll);
}
