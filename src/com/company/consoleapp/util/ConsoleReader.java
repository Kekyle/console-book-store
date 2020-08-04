package com.company.consoleapp.util;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String readString() {
        return scanner.next();
    }

    @Override
    public int readInt() {
        return scanner.nextInt();
    }
}
