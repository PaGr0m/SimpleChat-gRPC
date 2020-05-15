package com.jetbrains.university;

import com.jetbrains.university.ChatGrpc.ChatImplBase;
import io.grpc.stub.StreamObserver;

public class ChatGrpcImpl extends ChatImplBase {
    @Override
    public void chatStream(Mail request, StreamObserver<Mail> responseObserver) {
        super.chatStream(request, responseObserver);
    }
}