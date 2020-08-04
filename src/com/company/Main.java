package com.company;

import com.company.consoleapp.ConsoleApplication;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
            Application application = new ConsoleApplication();
            application.start();
    }
}
