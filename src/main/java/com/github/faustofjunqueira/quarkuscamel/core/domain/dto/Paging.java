package com.github.faustofjunqueira.quarkuscamel.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {
    private Integer offset;
    private Integer limit;
}
