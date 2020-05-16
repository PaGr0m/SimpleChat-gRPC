package com.jetbrains.university;

import com.jetbrains.university.ChatGrpc.ChatStub;
import com.jetbrains.university.util.ColorPrinter;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

public class ChatClient {
    private final ColorPrinter logger;
    private final ChatStub stub;
    private final SimpleConsoleChat chat;

    public ChatClient(String address, int port, ColorPrinter logger, String userName) {
        this.logger = logger;
        Channel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        stub = ChatGrpc.newStub(channel);
        chat = new SimpleConsoleChat(logger, userName);
    }

    public void runChat() {
        chat.chatStream(stub.chatStream(SimpleConsoleChat.getPrinterObserver(logger, null)));
    }

}
