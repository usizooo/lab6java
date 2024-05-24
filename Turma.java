package org.example;

import java.io.IOException;
import java.util.logging.Level;

public class Turma {
    private static log logInstance;

    static {
        try {
            logInstance = new log(Turma.class.getName(), "Turma.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        logInstance.logger.log(Level.INFO, "Запуск программы Turma");
        Menu menu = new Menu();
        menu.displayMenu();
        logInstance.logger.log(Level.INFO, "Программа Turma завершила работу");
    }
}
