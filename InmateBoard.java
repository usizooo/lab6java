package org.example;

import java.io.IOException;
import java.util.logging.Level;

class InmateBoard {
    private static log logInstance;

    static {
        try {
            logInstance = new log(InmateBoard.class.getName(), "InmateBoard.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMessage(String message) {
        logInstance.logger.log(Level.INFO, "Печать сообщения для заключенного: " + message);
        System.out.println("Сообщение для заключенных: " + message);
    }
}
