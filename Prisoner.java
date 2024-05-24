package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;

class Prisoner extends Person {
    private static log logInstance;
    private String crime;

    static {
        try {
            logInstance = new log(Prisoner.class.getName(), "Prisoner.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Prisoner(String id, char gender, LocalDate dateOfBirth, String crime) {
        super(id, gender, dateOfBirth);
        this.crime = crime;
        logInstance.logger.log(Level.INFO, "Создан новый объект Prisoner: " + id);
    }

    @Override
    public void displayInformation() {
        logInstance.logger.log(Level.INFO, "Отображение информации для Prisoner: " + id);
        System.out.println("ID заключенного: " + id);
        System.out.println("Пол: " + gender);
        System.out.println("Дата рождения: " + dateOfBirth);
        System.out.println("Преступление: " + crime);
    }
}

