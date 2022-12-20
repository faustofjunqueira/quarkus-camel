package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request.TaskCreateRequest;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.response.TaskResponse;
import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.mapper.TaskResourceMapper;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskCreateDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.dto.TaskFilterDto;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;

@Path("task")
@RequiredArgsConstructor
@RequestScoped
public class TaskResource {


    private final JsonWebToken jwt;
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
    @RolesAllowed({"task:create"})
    public Response create(TaskCreateRequest request) {
        TaskCreateDto dto = mapper.map(request);
        Task createdTask = svc.create(dto, jwt.getSubject());
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
    public Response delete(@RestPath String taskId) {
        svc.delete(taskId);
        return Response.noContent().build();
    }

    @Path("/{taskId}/completed")
    @ResponseStatus(RestResponse.StatusCode.NO_CONTENT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response complete(String taskId) {
        svc.complete(taskId);
        return Response.noContent().build();
    }

}
