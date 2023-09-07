package com.example.c195.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbURL = protocol + vendor + location + databaseName;
    private static final String driver  = "com.mysql.cj.jdbc.Driver";
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;

    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbURL, username, password);
            System.out.println("Connection Successful!");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
        connection.close();
            System.out.println("Connection Close!");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
