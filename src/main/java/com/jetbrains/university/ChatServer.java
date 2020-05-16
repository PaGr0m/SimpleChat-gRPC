package com.jetbrains.university;

import io.grpc.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ChatServer {

    private final Server server;
    private final int port;
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    public ChatServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new ChatGrpcImpl(logger)).build();

        if (server == null) {
            System.err.println("Error creating server");
            System.exit(1);
        }
    }

    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                ChatServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));

//        server.awaitTermination();
    }

    public void stop() throws InterruptedException {
        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }



}
