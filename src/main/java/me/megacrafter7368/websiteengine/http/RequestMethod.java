package me.megacrafter7368.websiteengine.http;

public enum RequestMethod {

    GET, POST, PUT, DELETE;

    // NULLABLE
    public static RequestMethod getByName(String name) {
        for (RequestMethod method : RequestMethod.values()) {
            if (method.name().equals(name)) {
                return method;
            }
        }
        return null;
    }

}