package com.github.faustofjunqueira.quarkuscamel.unit.application.adapter.controller;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.TaskResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

@QuarkusTest
@TestHTTPEndpoint(TaskResource.class)
public class TaskResourceTest {

    @Test
    public void shouldGetTaskListOverAllUsers() {
        when()
                .get()
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .log().all();

    }
}
