package com.jetbrains.university.util;

import org.apache.commons.cli.*;

public class Settings {
    private boolean isServer;
    private String address;
    private String userName;
    private int port;

    public Settings(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine parsed;
        try {
            parsed = parser.parse(CliMessages.cliOptions, args);
            if (parsed.getArgs().length > 0) {
                printHelpAndExit();
            }

            isServer = parsed.hasOption(CliMessages.SERVER_OPT);
            address = parsed.getOptionValue(CliMessages.CLIENT_OPT);
            userName = parsed.getOptionValue(CliMessages.USERNAME_OPT);
            port = Integer.parseInt(parsed.getOptionValue(CliMessages.PORT_OPT));

            if (userName == null || address != null && isServer || address == null && !isServer) {
                printHelpAndExit();
            }
        } catch (ParseException | NumberFormatException e) {
            printHelpAndExit();
        }
    }

    private void printHelpAndExit() {
        new HelpFormatter().printHelp(CliMessages.USAGE, CliMessages.cliOptions);
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
