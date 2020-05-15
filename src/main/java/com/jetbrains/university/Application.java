package com.jetbrains.university;


import com.jetbrains.university.util.Settings;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        Settings appSettings = new Settings(args);

        if (appSettings.isServer()) {
            ChatServer server = new ChatServer(appSettings.getPort());
            server.start();
        }
    }

}
