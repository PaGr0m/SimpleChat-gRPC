package com.jetbrains.university.util;

import java.util.Date;
import java.util.logging.Level;

public abstract class MailUtils {

    public static void printMail(ChatService.Mail mail, ColorPrinter output) {
        Date sendDate = new Date(mail.getSendTime().getSeconds() * 1000);
        String header = String.format(
                "[%s] %s :",
                mail.getSender(),
                sendDate.toString()
        );
        output.log(Level.SEVERE, header);
        output.println(mail.getText());
        output.println("");
    }

}
