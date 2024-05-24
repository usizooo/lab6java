package org.example;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Класс для настройки и использования логгера
public class log {
    public Logger logger; // Переменная для хранения экземпляра логгера
    FileHandler filehandler; // Переменная для хранения файлового обработчика

    // Конструктор класса log
    public log(String logger_name, String file_name) throws SecurityException, IOException {
        File file = new File(file_name); // Создаем файл с указанным именем (если он еще не существует)
        filehandler = new FileHandler(file_name, true); // Создаем файловый обработчик с указанным именем файла (добавляем в конец файла)
        logger = Logger.getLogger(logger_name); // Получаем экземпляр логгера с указанным именем
        logger.addHandler(filehandler); // Добавляем файловый обработчик к логгеру
        logger.setLevel(Level.ALL); // Устанавливаем уровень логирования на ALL для захвата всех сообщений
        SimpleFormatter formatter = new SimpleFormatter(); // Создаем простой форматтер для логирования
        filehandler.setFormatter(formatter); // Устанавливаем форматтер для файлового обработчика
    }
}
