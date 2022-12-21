package com.github.faustofjunqueira.quarkuscamel.application.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class ModelMapperConfig {

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
