package com.wh.config.rest;

public enum HttpMethod {

    GET,
    POST,
    PUT,
    DELETE,
    PATCH,
    HEAD,
    OPTIONS,
    BEFORE;

    public spark.route.HttpMethod toSparkMethod() {
        spark.route.HttpMethod httpMethod = null;
        switch (this) {
            case GET -> httpMethod = spark.route.HttpMethod.get;
            case POST -> httpMethod = spark.route.HttpMethod.post;
            case PUT -> httpMethod = spark.route.HttpMethod.put;
            case DELETE -> httpMethod = spark.route.HttpMethod.delete;
            case PATCH -> httpMethod = spark.route.HttpMethod.patch;
            case HEAD -> httpMethod = spark.route.HttpMethod.head;
            case OPTIONS -> httpMethod = spark.route.HttpMethod.options;
            case BEFORE -> httpMethod = spark.route.HttpMethod.before;
        }
        return httpMethod;
    }


}
