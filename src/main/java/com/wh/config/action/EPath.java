package com.wh.config.action;

import com.wh.config.rest.Path;
import lombok.Getter;
import lombok.Setter;

public enum EPath implements Path {

    // ADD PATHS HERE
    REST_TEST_PATH("/"),
    GQL_PATH("/graphql"),
    OPTIONS_PATH("/*"),
    BEFORE_PATH("/*");

    private final String path;

    EPath(String path) {
        this.path = path;
    }

    @Override
    public String getValue() {
        return this.path;
    }
}
