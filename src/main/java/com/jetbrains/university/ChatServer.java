package com.jetbrains.university;

import com.jetbrains.university.util.ColorPrinter;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

public class ChatServer {

    private final Server server;
    private final int port;
    private final ColorPrinter logger;

    public ChatServer(int port, ColorPrinter logger, String userName) {
        this.logger = logger;
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new SimpleConsoleChat(logger, userName)).build();

        if (server == null) {
            System.err.println("Error creating server");
            System.exit(1);
        }
    }

    public void start() throws IOException {
        server.start();
        logger.log(INFO, "Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.log(WARNING, "*** shutting down gRPC server since JVM is shutting down");
            try {
                ChatServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            logger.log(INFO, "*** server shut down");
        }));

    }

    public void stop() throws InterruptedException {
        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }

}
