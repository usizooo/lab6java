package org.example;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

class Guard {
    private static log logInstance;

    static {
        try {
            logInstance = new log(Guard.class.getName(), "Guard.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readGuardCommand() {
        logInstance.logger.log(Level.INFO, "Запрос ввода команды для охранника");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду для охранника:");
        String command = scanner.nextLine();
        logInstance.logger.log(Level.INFO, "Введенная команда для охранника: " + command);
        return command;
    }
}
