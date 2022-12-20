package com.github.faustofjunqueira.quarkuscamel.unit.application.adapter.controller;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.controller.TaskResource;
import com.github.faustofjunqueira.quarkuscamel.core.domain.model.Task;
import com.github.faustofjunqueira.quarkuscamel.core.port.service.TaskService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.oidc.server.OidcWiremockTestResource;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;
import io.vertx.core.json.JsonObject;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(TaskResource.class)
@QuarkusTestResource(OidcWiremockTestResource.class)
public class TaskResourceTest {

    @InjectMock
    private TaskService taskService;
    @TestHTTPResource
    @TestHTTPEndpoint(TaskResource.class)
    URL url;

    private final Task stubTask = new Task(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Dummy Title",
                "Foo Description",
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                null
        );

    @BeforeAll
    public void setup() {
        Mockito.when(taskService.create(Mockito.any(), Mockito.anyString())).thenReturn(stubTask);
    }

    @Test
    public void givenALoggedUser_whenCreatedRightTask_thenReceiveALinkToDetailTask() {

        var requestJson = new JsonObject();
        requestJson.put("title", stubTask.getTitle());
        requestJson.put("description", stubTask.getDescription());

        given()
                .auth().oauth2(generateJWT("junda", Set.of("task:create")))
                .header("Content-Type", ContentType.JSON)
                .body(requestJson.toString())
                .when()
                .post()
                .then()
                .statusCode(RestResponse.StatusCode.CREATED)
                .header("Location", url + "/" + stubTask.getId());
    }

    static String generateJWT(String userName, Set<String> roles) {
        return Jwt
                .issuer("https://server.example.com")
                .audience("https://service.example.com")
                .preferredUserName(userName)
                .groups(roles)
                .sign();
    }
}
