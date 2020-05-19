package com.jetbrains.university;

import com.jetbrains.university.chat.ChatStarter;
import com.jetbrains.university.util.ColorPrinter;
import com.jetbrains.university.util.Settings;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        ChatStarter chatStarter = new ChatStarter(new ColorPrinter(System.out), new Settings(args));
        chatStarter.runChat();
    }
}
