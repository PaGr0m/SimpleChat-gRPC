package com.jetbrains.university;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

public class ChatClient {

    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());
    private final String address;
    private final int port;

    public ChatClient(String address, int port) {
        this.address = address;
        this.port = port;

        Channel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        ChatGrpc.newStub(channel);
    }
}
