package com.jetbrains.university;

import com.google.protobuf.Timestamp;
import com.jetbrains.university.ChatGrpc.ChatImplBase;
import com.jetbrains.university.util.ColorPrinter;
import com.jetbrains.university.util.MailUtils;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.logging.Level;

public class SimpleConsoleChat extends ChatImplBase {
    private final ColorPrinter logger;
    private final String userName;

    public SimpleConsoleChat(ColorPrinter logger, String userName) {
        this.logger = logger;
        this.userName = userName;
    }

    public static StreamObserver<Mail> getPrinterObserver(ColorPrinter logger, StreamObserver<Mail> responseObserver) {
        return new StreamObserver<Mail>() {
            @Override
            public void onNext(Mail mail) {
                MailUtils.printMail(mail, logger);
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "ERROR IN CHAT");
            }

            @Override
            public void onCompleted() {
                if (responseObserver != null) {
                    responseObserver.onCompleted();
                }
                logger.log(Level.INFO, "BYE!");
            }
        };
    }

    private void runIOThread(StreamObserver<Mail> response) {
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                String line = sc.nextLine();
                if (line.equals("exit")) {
                    break;
                }
                response.onNext(Mail.newBuilder()
                        .setSender(userName).setText(line)
                        .setSendTime(
                                Timestamp.newBuilder()
                                        .setSeconds(System.currentTimeMillis() / 1000).build())
                        .build());
            }
        }).start();
    }

    @Override
    public StreamObserver<Mail> chatStream(StreamObserver<Mail> responseObserver) {
        logger.log(Level.FINE, "CONNECTED!");
        runIOThread(responseObserver);
        return getPrinterObserver(logger, responseObserver);
    }
}