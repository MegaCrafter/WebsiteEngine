package me.megacrafter7368.websiteengine.http;

import java.util.HashMap;
import java.util.Map;

public class HttpHeaders {

    private final Map<String, String> headers = new HashMap<>();

    public void parseAndAdd(String line) {
        line = line.trim().replaceAll(" +", " ");
        int index = line.indexOf(":");
        if (index == -1) throw new IllegalArgumentException("Line is not a header");

        headers.put(line.substring(0, index), line.substring(index+1));
    }

    public void put(String field, String value) {
        if (field.contains(":")) throw new IllegalArgumentException("Field cannot contain symbol ':'");
        headers.put(field, value);
    }

    public String get(String field) {
        return headers.get(field);
    }

    public boolean has(String field) {
        return headers.containsKey(field);
    }

    public Map<String, String> map() {
        return headers;
    }
}