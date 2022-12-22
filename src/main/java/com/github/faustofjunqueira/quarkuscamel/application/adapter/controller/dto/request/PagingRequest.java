package com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.reactive.RestQuery;

@Data
@NoArgsConstructor
public class PagingRequest {
    @RestQuery
    private Integer offset;
    @RestQuery
    private Integer limit;
}
