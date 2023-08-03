package com.wh.controller;

import com.wh.config.rest.HttpHandler;

public class RestController {

    // ADD CONTROLLER METHODS HERE
    public static final HttpHandler restTestRequestHandle = (request, response) -> response.body("OK222");
}
