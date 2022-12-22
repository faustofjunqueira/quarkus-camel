package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.reactive.RestQuery;

@Data
@NoArgsConstructor
public class TaskFilterRequest extends PagingRequest {

    @RestQuery
    private String userId;

}
