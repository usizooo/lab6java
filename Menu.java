package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;

class Menu {
    private Prison prison;
    private Guard guard;
    private InmateBoard inmateBoard;
    private static log logInstance;

    static {
        try {
            logInstance = new log(Menu.class.getName(), "Menu.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Menu() {
        this.prison = new Prison();
        this.guard = new Guard();
        this.inmateBoard = new InmateBoard();
    }

    // Метод для отображения меню и выполнения действий
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Меню:");
            System.out.println("1. Время с момента побега");
            System.out.println("2. Опасен ли заключенный");
            System.out.println("3. Пол заключенного");
            System.out.println("4. Количество заключенных");
            System.out.println("5. Дни до освобождения");
            System.out.println("6. Команда для охранника");
            System.out.println("7. Преступление заключенного");
            System.out.println("8. Время последнего побега");
            System.out.println("9. Возраст заключенного");
            System.out.println("10. Право на условно-досрочное освобождение");
            System.out.println("11. Введите сообщение для заключенного");
            System.out.println("12. Отобразить информацию о заключенном");
            System.out.println("0. Выход");

            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();
            logInstance.logger.log(Level.INFO, "Выбор пользователя: " + choice);

            switch (choice) {
                case 1:
                    System.out.println("Введите количество часов и минут с момента побега:");
                    if (scanner.hasNextInt()) {
                        int hours = scanner.nextInt();
                        if (scanner.hasNextInt()) {
                            int minutes = scanner.nextInt();
                            String result = Prison.timeSinceEscape(hours, minutes);
                            System.out.println(result);
                            logInstance.logger.log(Level.INFO, "Время с момента побега: " + result);
                        } else {
                            logInstance.logger.log(Level.WARNING, "Неверный формат. Введите число для минут.");
                            System.out.println("Неверный формат. Введите число для минут.");
                            scanner.next(); // Очистка буфера ввода
                        }
                    } else {
                        logInstance.logger.log(Level.WARNING, "Неверный формат. Введите число для часов.");
                        System.out.println("Неверный формат. Введите число для часов.");
                        scanner.next(); // Очистка буфера ввода
                    }
                    break;
                case 2:
                    System.out.println("Введите идентификатор заключенного:");
                    String prisonerID = scanner.next();
                    if (prisonerID.matches("(DNG|NON)ID\\d+")) {
                        boolean isDangerous = Prison.isDangerousPrisoner(prisonerID);
                        System.out.println(isDangerous);
                        logInstance.logger.log(Level.INFO, "Опасен ли заключенный " + prisonerID + ": " + isDangerous);
                    } else {
                        logInstance.logger.log(Level.WARNING, "Неверный формат идентификатора для опасного заключенного: " + prisonerID);
                        System.out.println("Неверный формат идентификатора. Идентификатор должен быть в формате DNGIDчисло или NONIDчисло.");
                    }
                    break;
                case 3:
                    System.out.println("Введите идентификатор заключенного:");
                    prisonerID = scanner.next();
                    if (prisonerID.matches("(M|F)?ID\\d+")) {
                        String gender = Prison.getPrisonerGender(prisonerID);
                        System.out.println(gender);
                        logInstance.logger.log(Level.INFO, "Пол заключенного " + prisonerID + ": " + gender);
                    } else {
                        logInstance.logger.log(Level.WARNING, "Неверный формат идентификатора для пола заключенного: " + prisonerID);
                        System.out.println("Неверный формат идентификатора. Идентификатор должен быть в формате MIDчисло, FIDчисло или IDчисло.");
                    }
                    break;
                case 4:
                    System.out.println("Введите идентификаторы заключенных через пробел:");
                    scanner.nextLine(); // Считываем символ новой строки после предыдущего ввода числа
                    String prisonersInput = scanner.nextLine().trim(); // Считываем строку с идентификаторами и убираем лишние пробелы
                    if (prisonersInput.isEmpty()) {
                        System.out.println("0");
                        logInstance.logger.log(Level.INFO, "Количество заключенных: 0");
                    } else {
                        String[] prisonerIDs = prisonersInput.split(" ");
                        boolean allIDsValid = true;
                        for (String id : prisonerIDs) {
                            if (!id.matches("ID\\d+")) {
                                allIDsValid = false;
                                break;
                            }
                        }
                        if (allIDsValid) {
                            int count = prison.countTotalPrisoners(prisonerIDs);
                            System.out.println(count);
                            logInstance.logger.log(Level.INFO, "Количество заключенных: " + count);
                        } else {
                            logInstance.logger.log(Level.WARNING, "Неверный формат одного или нескольких идентификаторов заключенных: " + prisonersInput);
                            System.out.println("Неверный формат идентификаторов. Каждый идентификатор должен быть в формате IDчисло.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Введите идентификатор заключенного:");
                    prisonerID = scanner.next();
                    if (prisonerID.matches("ID\\d+")) {
                        String timeUntilRelease = prison.timeUntilRelease(prisonerID);
                        System.out.println(timeUntilRelease);
                        logInstance.logger.log(Level.INFO, "Время до освобождения заключенного " + prisonerID + ": " + timeUntilRelease);
                    } else {
                        logInstance.logger.log(Level.WARNING, "Неверный формат идентификатора для времени до освобождения: " + prisonerID);
                        System.out.println("Неверный формат идентификатора. Идентификатор должен быть в формате IDчисло.");
                    }
                    break;
                case 6:
                    String guardCommand = guard.readGuardCommand();
                    System.out.println(guardCommand);
                    logInstance.logger.log(Level.INFO, "Команда для охранника: " + guardCommand);
                    break;
                case 7:
                    System.out.println("Введите идентификатор заключенного в формате IDчисло:");
                    prisonerID = scanner.next();
                    if (prisonerID.matches("ID\\d+")) {
                        String crime = prison.getPrisonerCrime(prisonerID);
                        System.out.println(crime);
                        logInstance.logger.log(Level.INFO, "Преступление заключенного " + prisonerID + ": " + crime);
                    } else {
                        logInstance.logger.log(Level.WARNING, "Неверный формат идентификатора для преступления заключенного: " + prisonerID);
                        System.out.println("Неверный формат идентификатора. Идентификатор должен быть в формате IDчисло.");
                    }
                    break;
                case 8:
                    String lastEscapeTime = prison.lastEscapeTime();
                    System.out.println(lastEscapeTime);
                    logInstance.logger.log(Level.INFO, "Время последнего побега: " + lastEscapeTime);
                    break;
                case 9:
                    System.out.println("Введите дату рождения заключенного в формате ГГГГ-ММ-ДД:");
                    String birthDateStr = scanner.next();
                    if (birthDateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        LocalDate birthDate = LocalDate.parse(birthDateStr);
                        int age = Prison.calculatePrisonerAge(birthDate);
                        System.out.println(age);
                        logInstance.logger.log(Level.INFO, "Возраст заключенного с датой рождения " + birthDateStr + ": " + age);
                    } else {
                        logInstance.logger.log(Level.WARNING, "Неверный формат даты рождения заключенного: " + birthDateStr);
                        System.out.println("Неверный формат даты. Введите дату в формате ГГГГ-ММ-ДД.");
                    }
                    break;
                case 10:
                    System.out.println("Введите идентификатор заключенного:");
                    prisonerID = scanner.next();
                    if (prisonerID.matches("(PAR)?ID\\d+")) {
                        boolean isEligible = Prison.isEligibleForParole(prisonerID);
                        System.out.println(isEligible);
                        logInstance.logger.log(Level.INFO, "Право на условно-досрочное освобождение заключенного " + prisonerID + ": " + isEligible);
                    } else {
                        logInstance.logger.log(Level.WARNING, "Неверный формат идентификатора для условно-досрочного освобождения: " + prisonerID);
                        System.out.println("Неверный формат идентификатора. Идентификатор должен быть в формате PARIDчисло или IDчисло.");
                    }
                    break;
                case 11:
                    scanner.nextLine(); // Считываем символ новой строки после выбора опции из меню
                    System.out.println("Введите сообщение для таблички заключенного:");
                    String message = scanner.nextLine(); // Считываем сообщение с консоли
                    inmateBoard.printMessage(message); // Вызываем функцию для печати сообщения на табличке заключенного
                    logInstance.logger.log(Level.INFO, "Сообщение для заключенного: " + message);
                    break;
                case 12:
                    System.out.println("Введите идентификатор заключенного:");
                    prisonerID = scanner.next();
                    if (prisonerID.matches("ID\\d+")) {
                        Prisoner prisoner = prison.getPrisonerById(prisonerID);
                        if (prisoner != null) {
                            prisoner.displayInformation();
                            logInstance.logger.log(Level.INFO, "Информация о заключенном " + prisonerID + " отображена.");
                        } else {
                            logInstance.logger.log(Level.WARNING, "Заключенный с идентификатором " + prisonerID + " не найден.");
                            System.out.println("Заключенный с таким идентификатором не найден.");
                        }
                    } else {
                        logInstance.logger.log(Level.WARNING, "Неверный формат идентификатора для информации о заключенном: " + prisonerID);
                        System.out.println("Неверный формат идентификатора. Идентификатор должен быть в формате IDчисло.");
                    }
                    break;
                case 0:
                    logInstance.logger.log(Level.INFO, "Программа завершена.");
                    System.out.println("Программа завершена.");
                    break;
                default:
                    logInstance.logger.log(Level.WARNING, "Неверный выбор: " + choice);
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
