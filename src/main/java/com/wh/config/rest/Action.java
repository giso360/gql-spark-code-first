package com.wh.config.rest;

public interface Action {

    Path getPath();

    HttpMethod getHttpMethod();

    HttpHandler getHttpHandler();



}
