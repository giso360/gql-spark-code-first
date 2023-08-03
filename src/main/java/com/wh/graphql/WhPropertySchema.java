package com.wh.graphql;

import com.wh.db.WhDb;
import com.wh.model.Property;
import com.wh.model.PropertyUnit;
import graphql.cachecontrol.CacheControl;
import graphql.execution.preparsed.persisted.InMemoryPersistedQueryCache;
import graphql.execution.preparsed.persisted.PersistedQueryCache;
import graphql.schema.*;

import java.io.IOException;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLNonNull.nonNull;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.PropertyDataFetcher.fetching;


public class WhPropertySchema {

    private static final DataFetcher getAllProperties = environment -> {
        System.out.println("getAllProperties invoked !!!");
        try {
            return WhDb.getInstance().getAllProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    private static final DataFetcher getOnePropertyByCode = environment -> {
        System.out.println("Get one property by Code invoked");
        String propertyCode = environment.getArgument("propertyCode");
        return WhDb.getInstance().findPropertyByCode(propertyCode);
    };

    /**
     * type PropertyUnitType implements Node {
     *     id: ID!
     *     # Short identifier code
     *     code: String!
     *     # Translatable text
     *     name: String!
     *     # Unit type, e.g. room, villa, apartment etc.
     *     unitType: String!
     *     minimumPersons: Int!
     *     maximumPersons: Int!
     * }
     */
    public static GraphQLObjectType propertyUnitType = newObject()
            .name("propertyUnit")
            // id
            .field(newFieldDefinition()
                    .type(GraphQLID)
                    .name("id")
                    .description("Property unit unique identifier")
                    .dataFetcher(fetching(PropertyUnit::getId))
                    .build())

            // code
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("code")
                    .description("Property unit code")
                    .dataFetcher(fetching(PropertyUnit::getCode))
                    .build())

            // name
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name")
                    .description("Property unit name")
                    .dataFetcher(fetching(PropertyUnit::getName))
                    .build())

            // unitType
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("unitType")
                    .description("Property unitType")
                    .dataFetcher(fetching(PropertyUnit::getUnitType))
                    .build())

            // minimumPersons
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("minimumPersons")
                    .description("Property unit minimumPersons")
                    .dataFetcher(fetching(PropertyUnit::getMinimumPersons))
                    .build())

            // maximumPersons
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("maximumPersons")
                    .description("Property unit maximumPersons")
                    .dataFetcher(fetching(PropertyUnit::getMaximumPersons))
                    .build())
            .build();

    /**
     * type Property {
     * code: String!
     * name: String!
     * type: String
     * rating: Int
     * unitTypes: [PropertyUnitType]!
     * }
     */
    public static GraphQLObjectType propertyType = newObject()
            .name("property")
            // code
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("code")
                    .description("property code")
                    .dataFetcher(fetching(Property::getCode))
                    .build())

            // name
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("name")
                    .description("property name")
                    .dataFetcher(fetching(Property::getName))
                    .build())

            // type
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("type")
                    .description("property type")
                    .dataFetcher(fetching(Property::getType))
                    .build())

            // rating
            .field(newFieldDefinition()
                    .type(GraphQLInt)
                    .name("rating")
                    .description("property rating")
                    .dataFetcher(fetching(Property::getRating))
                    .build())

            // unitTypes
            // TODO
            .field(newFieldDefinition()
                    .type(new GraphQLList(propertyUnitType))
                    .name("unitTypes")
                    .description("property units")
                    .dataFetcher(fetching(Property::getPropertyUnits))
                    .build())
            .build();

    public static GraphQLObjectType propertiesQueryType = newObject()
            .name("query")
            .field(newFieldDefinition()
                    .type(new GraphQLList(propertyType))
                    .name("properties")
                    .description("Return all properties")
                    .dataFetcher(getAllProperties)
                    .build())
            .build();

    public static GraphQLObjectType propertyByCodeQueryType = newObject()
            .name("query")
            .field(newFieldDefinition()
                    .type(propertyType)
                    .name("propertyByCode")
                    .description("Get one property By propertyCode")
                    .argument(newArgument().name("propertyCode").type(nonNull(GraphQLString)))
                    .dataFetcher(getOnePropertyByCode)
                    .build())
            .build();

    public static GraphQLSchema whPropertySchema = GraphQLSchema.newSchema()
            .query(newObject()
                    .name("Query")
                    .field(propertiesQueryType.getFieldDefinition("properties"))
                    .field(propertyByCodeQueryType.getFieldDefinition("propertyByCode"))
            )
            .build();
}
