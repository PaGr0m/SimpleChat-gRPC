package com.jetbrains.university.chat;

import com.jetbrains.university.util.ColorPrinter;
import com.jetbrains.university.util.Settings;

import java.io.IOException;

public class ChatStarter {
    private final Settings settings;
    private final ColorPrinter logger;

    public ChatStarter(ColorPrinter logger, Settings settings) {
        this.logger = logger;
        this.settings = settings;
    }

    public void runChat() throws IOException, InterruptedException {
        if (settings.isServer()) {
            runChatServer();
        } else {
            runChatClient();
        }
    }

    private void runChatServer() throws IOException, InterruptedException {
        ChatServer server = new ChatServer(logger, settings);
        server.start();
        server.blockUntilShutdown();
    }

    private void runChatClient() {
        ChatClient client = new ChatClient(logger, settings);
        client.start();
    }
}
