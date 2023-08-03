package com.wh.config.action;

import com.wh.config.rest.*;
import com.wh.controller.GqlController;
import com.wh.controller.RestController;
import lombok.Getter;

public enum EAction implements Action {

    // ADD ACTIONS HERE
    BEFORE_ACTION(HttpMethod.BEFORE, EPath.BEFORE_PATH, GqlController.graphQlBeforeRequestHandler),

    OPTIONS_ACTION(HttpMethod.OPTIONS, EPath.OPTIONS_PATH, GqlController.graphQlOptionsRequestHandler),

    // TEST - REST ENDPOINT
    REST_TEST(HttpMethod.GET, EPath.REST_TEST_PATH, RestController.restTestRequestHandle),

    // GRAPHQL
    GRAPHQL(HttpMethod.POST, EPath.GQL_PATH, GqlController.graphQlRequestHandler);

    @Override
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    @Override
    public Path getPath() {
        return this.path;
    }

    EAction(HttpMethod httpMethod, Path path, HttpHandler httpHandler) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.httpHandler = httpHandler;
    }

    @Getter
    private final HttpMethod httpMethod;

    @Getter
    private final Path path;

    @Getter
    private final HttpHandler httpHandler;
}
