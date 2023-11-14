package com.example.c195.helper;

import com.example.c195.dao.UserQuery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log {
    public static void accessLog(String username, int successful) {
        LocalDateTime time = LocalDateTime.now();
        BufferedWriter writer = null;

            try {
                writer = new BufferedWriter(new FileWriter("login_activity.txt", true));

                String log;
                if (successful != -1) {
                    log = "Username: " + username + "Time/Date: " + time + " Allowed";
                } else {
                    log = "Username: " + username + "Time/Date: " + time + " Denied";
                }
                writer.write(log + System.lineSeparator());
            } catch(Exception e) {
                System.out.println(e);
            } finally {
                try {
                    if(writer != null) {
                        writer.close();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            }
}
