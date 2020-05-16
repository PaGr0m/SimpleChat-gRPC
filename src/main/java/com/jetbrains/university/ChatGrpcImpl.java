package com.jetbrains.university;

import com.jetbrains.university.ChatGrpc.ChatImplBase;
import com.jetbrains.university.ChatService.Mail;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatGrpcImpl extends ChatImplBase {

    private final ConcurrentLinkedQueue<Mail> mails = new ConcurrentLinkedQueue<>();
    private final Logger logger;

    public ChatGrpcImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public StreamObserver<Mail> chatStream(StreamObserver<Mail> responseObserver) {
        return new StreamObserver<Mail>() {
            @Override
            public void onNext(Mail mail) {
                mails.add(mail);
                System.out.println(mail.toString());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Encountered error in chat", t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}