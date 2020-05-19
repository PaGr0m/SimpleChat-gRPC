package com.jetbrains.university.chat;

import com.jetbrains.university.util.ColorPrinter;
import com.jetbrains.university.util.LogMessages;
import com.jetbrains.university.util.Settings;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class ChatServer {
    private final ColorPrinter logger;
    private final Settings settings;
    private final Server server;

    public ChatServer(ColorPrinter logger, @NotNull Settings settings) {
        this.logger = logger;
        this.settings = settings;
        this.server = ServerBuilder.forPort(settings.getPort())
                                   .addService(new SimpleConsoleChat(logger, settings.getUserName()))
                                   .build();

        if (server == null) {
            logger.log(Level.SEVERE, LogMessages.SERVER_ERROR);
            System.exit(1);
        }
    }

    public void start() throws IOException {
        server.start();
        logger.log(Level.INFO, String.format(LogMessages.SERVER_STARTED_ON, settings.getPort()));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.log(Level.WARNING, LogMessages.SERVER_SHUT_DOWN_JVM);
            try {
                ChatServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            logger.log(Level.INFO, LogMessages.SERVER_SHUT_DOWN);
        }));
    }

    public void stop() throws InterruptedException {
        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }
}
