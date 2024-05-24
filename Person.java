package org.example;

import java.time.LocalDate;
import java.io.IOException;
import java.util.logging.Level;

abstract class Person {
    protected String id;
    protected char gender;
    protected LocalDate dateOfBirth;
    private static log logInstance;

    static {
        try {
            logInstance = new log(Person.class.getName(), "Person.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person(String id, char gender, LocalDate dateOfBirth) {
        this.id = id;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        logInstance.logger.log(Level.INFO, "Создан объект Person: " + id);
    }

    public abstract void displayInformation();
}

