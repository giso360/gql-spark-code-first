package com.wh.util;

import com.google.gson.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class GraphQLUtil {

    private static final String UTC_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static String JSON_FIELD_NAMING_POLICY = null;
    private static final SimpleDateFormat UTC_DATE_FORMAT = new SimpleDateFormat(UTC_DATE_PATTERN);



    public static GraphQLRequest getGraphQLRequest (String payload) {
        JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        GraphQLRequest graphQLRequest = GsonInstances.GSON.fromJson(jsonObject, GraphQLRequest.class);
        graphQLRequest.setBodyJsonObject(jsonObject);
        return graphQLRequest;
    }



    private static GsonBuilder getGsonBuilder(final boolean serializeNulls) {
        final GsonBuilder gsonBuilder = new GsonBuilder()
                .setDateFormat(UTC_DATE_PATTERN)
                .setObjectToNumberStrategy(ToNumberPolicy.BIG_DECIMAL)
                .disableHtmlEscaping();

        if ( serializeNulls ) {
            gsonBuilder.serializeNulls();
        }

        Optional.ofNullable(JSON_FIELD_NAMING_POLICY).ifPresent(value -> {
            gsonBuilder.setFieldNamingStrategy(FieldNamingPolicy.valueOf(value));
        });

        // TODO: review this
        gsonBuilder.registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) ->
                new JsonPrimitive((int) Math.round(src))
        );

        gsonBuilder.registerTypeAdapter(Timestamp.class, (JsonSerializer<Timestamp>) (src, typeOfSrc, context) ->
                new JsonPrimitive(UTC_DATE_FORMAT.format(src))
        );

        gsonBuilder.registerTypeAdapter(java.sql.Date.class, (JsonSerializer<java.sql.Date>) (src, typeOfSrc, context) ->
                new JsonPrimitive(src.toString())
        );

//        gsonBuilder
//                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
//                .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer())
//                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeSerializer())
//                .registerTypeAdapter(OffsetTime.class, new OffsetTimeSerializer())
//                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer())
//                .registerTypeAdapter(Instant.class, new InstantSerializer())
//                .registerTypeAdapter(Boolean.class, new BooleanSerializer())
//                .registerTypeAdapter(boolean.class, new BooleanSerializer())
//                .registerTypeAdapter(String.class, new StringSerializer())
//                .registerTypeAdapter(Integer.class, new IntegerSerializer())
//                .registerTypeAdapter(int.class, new IntegerSerializer())
//                .registerTypeAdapter(Float.class, new FloatSerializer())
//                .registerTypeAdapter(float.class, new FloatSerializer())
//                .registerTypeAdapter(Double.class, new DoubleSerializer())
//                .registerTypeAdapter(double.class, new DoubleSerializer())
//                .registerTypeAdapter(BigDecimal.class, new BigDecimalSerializer())
//                .registerTypeAdapter(AtomicInteger.class, new AtomicIntegerSerializer())
//                .registerTypeAdapterFactory(OptionalTypeAdapter.FACTORY);

        return gsonBuilder;
    }


    private static class GsonInstances {
        public static Gson GSON = getGsonBuilder(true).create();
    }



}
