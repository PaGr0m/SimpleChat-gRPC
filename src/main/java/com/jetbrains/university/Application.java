package com.jetbrains.university;


import com.jetbrains.university.util.Settings;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        Settings appSettings = new Settings(args);

        if (appSettings.isServer()) {
            System.out.println(">> Starting server on port " + appSettings.getPort());
            ChatServer server = new ChatServer(appSettings.getPort());
            server.start();
            server.blockUntilShutdown();
        } else {
            System.out.println(">> Starting client, connecting to "
                    + appSettings.getAddress() + ":" + appSettings.getPort());
            ChatClient client = new ChatClient(appSettings.getAddress(), appSettings.getPort());
        }
    }

}
