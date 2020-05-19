package com.jetbrains.university.util;

import org.apache.commons.cli.Options;

public class CliMessages {
    private CliMessages() {}

    public static final Options cliOptions = new Options();

    public static final String USAGE = "./chat <OPTIONS>";

    public static final String SERVER_OPT = "s";
    public static final String CLIENT_OPT = "c";
    public static final String PORT_OPT = "p";
    public static final String USERNAME_OPT = "u";

    private static final String SERVER_NAME = "server";
    private static final String CLIENT_NAME = "client";
    private static final String PORT_NAME = "port";
    private static final String USERNAME_NAME = "user";

    private static final String SERVER_DESCRIPTION = "run application in server mode";
    private static final String CLIENT_DESCRIPTION = "run application in client mode";
    private static final String PORT_DESCRIPTION = "specify the port number";
    private static final String USERNAME_DESCRIPTION = "set user name";

    static {
        cliOptions.addOption(SERVER_OPT, SERVER_NAME, false, SERVER_DESCRIPTION);
        cliOptions.addOption(CLIENT_OPT, CLIENT_NAME, true, CLIENT_DESCRIPTION);
        cliOptions.addOption(PORT_OPT, PORT_NAME, true, PORT_DESCRIPTION);
        cliOptions.addOption(USERNAME_OPT, USERNAME_NAME, true, USERNAME_DESCRIPTION);
    }
}
