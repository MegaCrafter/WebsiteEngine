package me.megacrafter7368.websiteengine.http;

import me.megacrafter7368.websiteengine.utils.FileReader;

import java.io.*;
import java.util.Map;

public class HttpResponse {

    private int statusCode;
    private String statusMessage;
    private final HttpHeaders headers;
    private String body;

    public HttpResponse() {
        this.statusCode = 500;
        this.statusMessage = "";
        this.headers = new HttpHeaders();
        this.body = "";
    }

    public int statusCode() {
        return statusCode;
    }

    public HttpHeaders headers() {
        return headers;
    }

    public String body() {
        return body;
    }

    public String statusMessage() {
        return statusMessage;
    }

    public void statusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void statusMessage(String message) {
         this.statusMessage = message;
    }

    public void body(String body) {
        this.body = body;
    }

    public void body(File file) {
        this.body = FileReader.read(file);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("HTTP/1.1 ").append(this.statusCode).append(" ").append(this.statusMessage).append("\n");

        for (Map.Entry<String, String> entry : this.headers.map().entrySet()) {
            builder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        builder.append("\n").append(this.body);

        return builder.toString();
    }
}