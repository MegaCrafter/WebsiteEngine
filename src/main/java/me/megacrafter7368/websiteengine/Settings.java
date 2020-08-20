package me.megacrafter7368.websiteengine;

import me.megacrafter7368.websiteengine.utils.FileReader;
import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Settings {

    private Map<String, String> routes = new HashMap<>();

    public Settings() {
        try {
            File file = new File(Settings.class.getResource("/settings.json").toURI());
            JSONObject object = new JSONObject(FileReader.read(file));
            JSONObject routesObj = object.getJSONObject("routes");

            Map<String, Object> map = routesObj.toMap();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (!(entry.getValue() instanceof String)) throw new IllegalArgumentException("Illegal value for route in settings!");

                this.routes.put(entry.getKey(), (String) entry.getValue());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public File getRoute(String route) {
        String fileName = this.routes.get(route);
        try {
            URL url = Settings.class.getResource("/public/" + fileName);
            if (url == null) return null;

            return new File(url.toURI());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}