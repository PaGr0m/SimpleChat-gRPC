package com.jetbrains.university.chat;

import com.jetbrains.university.ChatGrpc;
import com.jetbrains.university.util.ColorPrinter;
import com.jetbrains.university.util.LogMessages;
import com.jetbrains.university.util.Settings;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class ChatClient {
    private final ColorPrinter logger;
    private final Settings settings;

    public ChatClient(ColorPrinter logger, @NotNull Settings settings) {
        this.logger = logger;
        this.settings = settings;
    }

    public void start() {
        logger.log(Level.INFO, String.format(LogMessages.CLIENT_CONNECT_TO,
                                             settings.getAddress(),
                                             settings.getPort()));

        Channel channel = ManagedChannelBuilder.forAddress(settings.getAddress(),
                                                           settings.getPort())
                                               .usePlaintext()
                                               .build();
        SimpleConsoleChat chat = new SimpleConsoleChat(logger, settings.getUserName());
        chat.chatStream(ChatGrpc.newStub(channel)
                                .chatStream(SimpleConsoleChat.getPrinterObserver(logger, null)));
    }
}
