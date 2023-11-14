package com.example.c195.helper;

import com.example.c195.dao.UserQuery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This helper class contains a function that outputs to a file "login_activity.txt" in the root of this program.
 * It records successful and unsuccessful login attempts.
 */
public class Log {
    /**
     * This function writes output to a file "login_activity.txt" in the root of this program.
     * It records successful and unsuccessful login attempts. It appends each attempt to the file.
     * @param username username of the person logging in
     * @param successful if a valid loginID is provided this will write the success message. If not it will
     *                   write the denied message to the file.
     */
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
