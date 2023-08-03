package com.wh.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.language.SourceLocation;

import java.util.List;

public class WhGenericGQLException extends GraphQLException implements GraphQLError {

    private String errorMsg;

    public WhGenericGQLException(String message) {
        super(message);
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }


}
