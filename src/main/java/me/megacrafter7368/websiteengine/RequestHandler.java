package me.megacrafter7368.websiteengine;

import me.megacrafter7368.websiteengine.http.HttpRequest;
import me.megacrafter7368.websiteengine.http.HttpResponse;
import me.megacrafter7368.websiteengine.http.RequestMethod;

import java.io.File;

public class RequestHandler {

    public static void handle(HttpRequest request, HttpResponse response) {
        if (request.method() == RequestMethod.GET) {
            File file = Core.settings().getRoute(request.route());
            if (file == null) {
                response.statusCode(404);
                response.statusMessage("ROUTE NOT FOUND");
                return;
            }

            String path = file.getAbsolutePath();

            int index = path.lastIndexOf(".");
            if (!path.endsWith(".") && index != -1) {
                String type = path.substring(index+1);

                if (type.equals("html") || type.equals("css")) {
                    response.headers().put("Content-Type", "text/"+type + "; charset=utf-8");

                } else if (type.equals("json")) {
                    response.headers().put("Content-Type", "application/json; charset=utf-8");

                }
            }

            response.statusCode(200);
            response.body(file);
        }
    }

}