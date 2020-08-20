package me.megacrafter7368.websiteengine.http;

import java.util.logging.Logger;

public class HttpRequest {

    public static HttpRequest parse(String request) {
        String[] lines = request.trim().replaceAll(" +", " ").split("\n");
        if (lines.length == 0) throw new IllegalArgumentException("No lines in request!");

        String firstLine = lines[0];
        String[] reqInfo = firstLine.split("\\s");

        System.out.println(firstLine);

        if (reqInfo.length != 3) throw new IllegalArgumentException("Malformed request information!");

        RequestMethod method = RequestMethod.getByName(reqInfo[0]);
        if (method == null) throw new IllegalArgumentException(reqInfo[0] + " request method cannot be resolved!");

        String route = reqInfo[1];

        HttpHeaders headers = new HttpHeaders();

        int i;
        for (i = 1; i < lines.length; i++) {
            String line = lines[i];
            if (line.isEmpty()) break;

            headers.parseAndAdd(line);
        }

        StringBuilder bodyBuilder = new StringBuilder();
        for (i++; i < lines.length; i++) {
            bodyBuilder.append(lines[i]).append("\n");
        }

        return new HttpRequest(method, route, headers, bodyBuilder.toString());
    }

    private final RequestMethod method;
    private final String route;
    private final HttpHeaders headers;
    private final String body;

    public HttpRequest(RequestMethod method, String route, HttpHeaders headers, String body) {
        this.method = method;
        this.route = route;
        this.headers = headers;
        this.body = body;
    }

    public RequestMethod method() {
        return method;
    }

    public String route() {
        return route;
    }

    public HttpHeaders headers() {
        return headers;
    }

    public String body() {
        return body;
    }
}