package me.megacrafter7368.websiteengine;

import me.megacrafter7368.websiteengine.http.HttpRequest;
import me.megacrafter7368.websiteengine.http.HttpResponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerListener {

    private final int port;

    public ServerListener(int port) {
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket server = new ServerSocket(this.port);

            while (true) {
                Socket socket = server.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

                StringBuilder requestBuilder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    requestBuilder.append(line).append("\n");
                }

                HttpRequest request = HttpRequest.parse(requestBuilder.toString());
                HttpResponse response = new HttpResponse();

                RequestHandler.handle(request, response);

                writer.write(response.toString());
                writer.flush();

                writer.close();
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}