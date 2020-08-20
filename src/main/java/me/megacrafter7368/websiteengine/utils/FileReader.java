package me.megacrafter7368.websiteengine.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileReader {

    public static String read(File file) {
        StringBuilder bodyBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null) {
                bodyBuilder.append(line).append("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bodyBuilder.toString();
    }

}