package me.megacrafter7368.websiteengine;

public class Core {

    private static Settings settings;

    public static void main(String[] args) {
        settings = new Settings();

        ServerListener handler = new ServerListener(3000);
        handler.start();
    }

    public static Settings settings() {
        return settings;
    }
}