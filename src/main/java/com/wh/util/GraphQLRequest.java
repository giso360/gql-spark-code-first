package com.wh.util;

import com.google.gson.JsonObject;
import lombok.Data;

import java.util.Map;

@Data
public class GraphQLRequest {

    private transient JsonObject bodyJsonObject;
    private String operationName;
    private Map<String, Object> variables;
    private String query;
}
