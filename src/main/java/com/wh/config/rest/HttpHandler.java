package com.wh.config.rest;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.RouteImpl;

public interface HttpHandler  {

    void handleRequest(final Request request, final Response response);

}
