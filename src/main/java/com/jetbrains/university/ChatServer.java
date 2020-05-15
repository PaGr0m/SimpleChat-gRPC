package com.jetbrains.university;

import io.grpc.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ChatServer {

    private final Server server;

    public ChatServer(int port) {
        this.server = ServerBuilder.forPort(port).build();
    }

    public void start() throws IOException, InterruptedException {
        server.start();

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

}
