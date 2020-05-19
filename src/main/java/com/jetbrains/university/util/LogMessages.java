package com.jetbrains.university.util;

public class LogMessages {
    private LogMessages() {}

    public static final String SERVER_STARTED_ON = "Server started, listening on %d";
    public static final String SERVER_SHUT_DOWN = "*** server shut down";
    public static final String SERVER_SHUT_DOWN_JVM = "*** shutting down gRPC server since JVM is shutting down";
    public static final String SERVER_ERROR = "Error creating server";

    public static final String CLIENT_CONNECT_TO = "Starting client, connecting to %s : %d";

    public static final String CHAT_CONNECTED = "CONNECTED!";
    public static final String CHAT_ERROR = "ERROR IN CHAT";
    public static final String CHAT_GOOD_BYE = "GOOD BYE!";
}
