package com.company.consoleapp.util;

public class ConsoleWriter implements Writer {
    @Override
    public void write(String value) {
        System.out.print(value);
    }
}
