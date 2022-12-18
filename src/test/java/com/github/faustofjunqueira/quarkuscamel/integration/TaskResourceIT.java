package com.github.faustofjunqueira.quarkuscamel.integration;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.TaskResource;
import com.github.faustofjunqueira.quarkuscamel.unit.application.adapter.controller.TaskResourceTest;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
@TestHTTPEndpoint(TaskResource.class)
public class TaskResourceIT {

    @Test
    public void shouldGetTaskListOverAllUsers() {
        when()
                .get()
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .log().all();

    }

}
