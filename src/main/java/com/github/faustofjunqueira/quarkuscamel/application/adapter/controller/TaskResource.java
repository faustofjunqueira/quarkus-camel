package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request.TaskCreateRequest;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request.TaskFilterRequest;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.response.PageResponse;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.response.TaskResponse;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.Paging;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.modelmapper.ModelMapper;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("task")
@RequiredArgsConstructor
@RequestScoped
public class TaskResource {


    private final JsonWebToken jwt;
    private final TaskService svc;

    private final ModelMapper mapper;

    @GET
    @ResponseStatus(RestResponse.StatusCode.OK)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PageResponse<TaskResponse> list(TaskFilterRequest request) {
        var filter = mapper.map(request, TaskFilterDto.class);
        var pagingResult = svc.list(filter);
        return PageResponse.<TaskResponse>builder()
                .total(pagingResult.getTotal())
                .data(pagingResult.getData().stream().map(t -> mapper.map(t, TaskResponse.class)).collect(Collectors.toList()))
                .build();
    }

    @GET
    @Path("{taskId}")
    @ResponseStatus(RestResponse.StatusCode.OK)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TaskResponse getById(
            @RestPath String taskId
    ) {
        return mapper.map(svc.getById(UUID.fromString(taskId)), TaskResponse.class);
    }

    @POST
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"task:create"})
    @Transactional
    public Response create(TaskCreateRequest request) {
        Task createdTask = svc.create(mapper.map(request, TaskCreateDto.class), UUID.fromString(jwt.getSubject()));
        URI createdResourceUri = UriBuilder.fromResource(TaskResource.class)
                .path("/" + createdTask.getId())
                .build();

        return Response.created(createdResourceUri).build();
    }

    @DELETE
    @Path("/{taskId}")
    @ResponseStatus(RestResponse.StatusCode.NO_CONTENT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@RestPath String taskId) {
        svc.delete(UUID.fromString(taskId));
        return Response.noContent().build();
    }

    @POST
    @Path("/{taskId}/completed")
    @ResponseStatus(RestResponse.StatusCode.NO_CONTENT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response complete(String taskId) {
        svc.complete(UUID.fromString(taskId));
        return Response.noContent().build();
    }

}
