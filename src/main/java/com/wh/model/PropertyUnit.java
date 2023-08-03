package com.wh.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class PropertyUnit {

    public static final PropertyUnit ERROR_PROPERTY_UNIT = PropertyUnit.builder().
            id(new UUID(0L, 0L)).
            code("").
            name("").
            unitType("").
            minimumPersons(-1).
            maximumPersons(-1).
            build();

    /**
     *     GQL:
     *     -- type PropertyUnit --
     *     id: ID!
     *     # Short identifier code
     *     code: String!
     *     name: String!
     *     # Unit type, e.g. room, villa, apartment etc.
     *     unitType: String!
     *     minimumPersons: Int!
     */

    private UUID id;

    private String code;

    private String name;

    private String unitType;

    private int minimumPersons;

    private int maximumPersons;

}
