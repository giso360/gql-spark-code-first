package com.wh;

import com.wh.config.properties.SettingsUtils;
import com.wh.config.action.EAction;
import com.wh.db.WhDb;
import spark.*;

import java.io.IOException;
import java.util.Properties;

import static spark.Spark.options;

public class App {

    private static final Service SPARK = Service.ignite();

    private static final Properties PROPERTIES = new SettingsUtils().getProps();

    public static void main(String[] args) throws IOException {
        WhDb db = WhDb.getInstance();
        System.out.println(db.getAllProperties());
        int portNo = Integer.parseInt(PROPERTIES.get("port").toString());
        SPARK.port(portNo);
//
//        Filter filter = (request, response) -> {
//            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
//            if (accessControlRequestHeaders != null) {
//                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
//            }
//            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
//            if(accessControlRequestMethod != null){
//                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
//            }
//
//        };

//        Filter filter = (request, response) -> {
//            response.header("Access-Control-Allow-Origin", "*");
//        };
//
//        SPARK.before(filter);
        initializeRoutes(EAction.values());

    }

    private static void initializeRoutes(final EAction... actions) {
        for (EAction action : actions) {
            final Route route = (request, response) -> {
                action.getHttpHandler().handleRequest(request, response);
                return response.body();
            };
            addAction(action, route);
//            if (action == EAction.GRAPHQL){
//                final Route optionsRoute = (request, response) -> {
//                    String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
//                    if (accessControlRequestHeaders != null) {
//                        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
//                    }
//                    String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
//                    if(accessControlRequestMethod != null){
//                        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
//                    }
//                    return "OK";
//                };
//                addAction();
//            }
        }
    }

    private static void addAction(EAction action, Route route) {
        SPARK.addRoute(action.getHttpMethod().toSparkMethod(), RouteImpl.create(action.getPath().getValue(), "*/*", route));
    }
}
