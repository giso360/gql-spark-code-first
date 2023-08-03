package com.wh.exceptions;

public class WhPropertyNotFoundException extends WhGenericGQLException {
    public WhPropertyNotFoundException(String requestedPropertyCode) {
        super(String.format("Property %s not found", requestedPropertyCode));
    }

}
