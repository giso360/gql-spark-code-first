package com.wh.controller;

import com.google.gson.Gson;
import com.wh.config.rest.HttpHandler;
import com.wh.graphql.WhPropertySchema;
import com.wh.util.GraphQLRequest;
import com.wh.util.GraphQLUtil;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

import java.util.Map;

public class GqlController {

    private static final Gson GSON = new Gson();

    private static final GraphQL GRAPHQL = GraphQL.newGraphQL(WhPropertySchema.whPropertySchema).build();

    public static final HttpHandler graphQlOptionsRequestHandler = (request, response) -> {
        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
        }
        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
        if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
        }
        response.body("OK");
    };

    public static final HttpHandler graphQlBeforeRequestHandler = (request, response) -> {
        response.header("Access-Control-Allow-Origin", "*");
    };

    public static final HttpHandler graphQlRequestHandler = (request, response) -> {
        response.type("application/json");
        GraphQLRequest graphQLRequest = GraphQLUtil.getGraphQLRequest(request.body());
        ExecutionInput.Builder builder = ExecutionInput.newExecutionInput().query(graphQLRequest.getQuery());
        if ( graphQLRequest.getVariables() != null ) {
            builder.variables(graphQLRequest.getVariables());
        }
        ExecutionResult executionResult = GRAPHQL.execute(builder);
        Map<String, Object> responseData = executionResult.toSpecification();
        response.body(GSON.toJson(responseData));
    };
}
