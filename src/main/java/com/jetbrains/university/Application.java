package com.jetbrains.university;


import com.jetbrains.university.util.ColorPrinter;
import com.jetbrains.university.util.Settings;

import java.io.IOException;
import java.util.logging.Level;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        Settings appSettings = new Settings(args);
        ColorPrinter logger = new ColorPrinter(System.out);

        if (appSettings.isServer()) {
            logger.log(Level.INFO, "Starting server on port " + appSettings.getPort());
            ChatServer server = new ChatServer(
                    appSettings.getPort(),
                    logger,
                    appSettings.getUserName()
            );
            server.start();
            server.blockUntilShutdown();
        } else {
            logger.log(Level.INFO, "Starting client, connecting to "
                    + appSettings.getAddress() + ":" + appSettings.getPort());
            ChatClient client = new ChatClient(
                    appSettings.getAddress(),
                    appSettings.getPort(),
                    logger,
                    appSettings.getUserName()
            );
            client.runChat();
        }
    }

}
