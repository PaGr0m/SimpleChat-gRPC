package com.jetbrains.university.util;

import org.apache.commons.cli.*;

public class Settings {
    private final static String USAGE = "./chat <OPTIONS>";
    private final static String SERVER_OPT = "s";
    private final static String CLIENT_OPT = "c";
    private final static String PORT_OPT = "p";
    private final static String USERNAME_OPT = "u";
    private static final Options cliOptions = new Options();

    static {
        cliOptions.addOption(
                SERVER_OPT, "server", false,
                "run application in server mode"
        );
        cliOptions.addOption(
                CLIENT_OPT, "client", true,
                "run application in client mode"
        );
        cliOptions.addOption(
                PORT_OPT, "port", true,
                "specify the port number"
        );
        cliOptions.addOption(
                USERNAME_OPT, "user", true,
                "set user name"
        );
    }

    private boolean isServer;
    private String address;
    private int port;
    private String userName;

    public Settings(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine parsed = null;

        try {
            parsed = parser.parse(cliOptions, args);
            if (parsed.getArgs().length > 0) {
                printHelpAndExit();
            }

            isServer = parsed.hasOption(SERVER_OPT);
            address = parsed.getOptionValue(CLIENT_OPT);
            port = Integer.parseInt(parsed.getOptionValue(PORT_OPT));
            userName = parsed.getOptionValue(USERNAME_OPT);

            if (userName == null || address != null && isServer || address == null && !isServer) {
                printHelpAndExit();
            }
        } catch (ParseException | NumberFormatException e) {
            printHelpAndExit();
        }
    }

    private void printHelpAndExit() {
        new HelpFormatter().printHelp(USAGE, cliOptions);
        System.exit(1);
    }

    public boolean isServer() {
        return isServer;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }
}
