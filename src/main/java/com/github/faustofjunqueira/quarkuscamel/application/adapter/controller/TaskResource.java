package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request.TaskCreateRequest;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.response.TaskResponse;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.mapper.TaskResourceMapper;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("task")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService svc;

    private final TaskResourceMapper mapper;

    @GET
    @ResponseStatus(RestResponse.StatusCode.OK)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TaskResponse> list(
            @RestQuery String userId
    ) {
        var filter = new TaskFilterDto();
        if(userId != null) {
            filter.setUserId(userId);
        }
        return mapper.map(svc.list(filter));
    }

    @POST
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Task create(TaskCreateRequest dto) {
        // Depends On: Authentication
        return null;
    }

    @DELETE
    @Path("/{taskId}")
    @ResponseStatus(RestResponse.StatusCode.NO_CONTENT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@RestPath String taskId) {
        svc.delete(taskId);
    }

    @Path("/{taskId}/completed")
    @ResponseStatus(RestResponse.StatusCode.NO_CONTENT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void complete(String taskId) {
        svc.complete(taskId);
    }

}
