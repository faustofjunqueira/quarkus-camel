package com.github.faustofjunqueira.quarkuscamel.core.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Page<T> {

    private long total;
    private List<T> data;
}
