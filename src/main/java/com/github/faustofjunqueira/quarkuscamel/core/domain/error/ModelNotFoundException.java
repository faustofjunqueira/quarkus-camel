package com.github.faustofjunqueira.quarkuscamel.core.domain.error;

public class ModelNotFoundException extends RuntimeException{

    private static final String MSG_FORMAT = "%s not found";
    private static final String MSG_FORMAT_BY_ID = "%s. ID=%s";

    public ModelNotFoundException(String entityName, Object id) {
        super(String.format(MSG_FORMAT_BY_ID, String.format(MSG_FORMAT, entityName), id.toString()));
    }

    public ModelNotFoundException(String entityName) {
        super(String.format(MSG_FORMAT, entityName));
    }

}
