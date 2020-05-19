package com.jetbrains.university.util;

import com.jetbrains.university.ChatService.Mail;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.logging.Level;

public abstract class MailUtils {

    public static void printMail(@NotNull Mail mail, @NotNull ColorPrinter output) {
        Date sendDate = new Date(mail.getSendTime().getSeconds() * 1000);
        String header = String.format("[%s] %s :",
                                      mail.getSender(),
                                      sendDate.toString());
        output.log(Level.SEVERE, header);
        output.println(mail.getText());
        output.println("");
    }
}
