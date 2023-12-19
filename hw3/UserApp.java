package hw3;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class UserApp {

    public static void main(String[] args) {
        // Создаем объект Scanner для считывания ввода пользователя
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем у пользователя данные
        System.out.println("Введите следующие данные в произвольном порядке, разделенные пробелом:");
        System.out.println("Фамилия Имя Отчество датарождения номертелефона пол");

        // Считываем строку ввода
        String input = scanner.nextLine();

        // Закрываем объект Scanner
        scanner.close();

        // Разбиваем строку ввода на массив слов
        String[] words = input.split(" ");

        // Проверяем, что количество слов равно шести
        if (words.length != 6) {
            // Выводим сообщение об ошибке и завершаем программу
            System.out.println("Вы ввели " + words.length + " данных, а нужно 6.");
            System.exit(1);
        }

        // Объявляем переменные для хранения данных пользователя
        String surname = null;
        String name = null;
        String patronymic = null;
        String birthDate = null;
        long phoneNumber = 0;
        char gender = '\0';

        // Создаем шаблоны регулярных выражений для проверки форматов данных
        Pattern surnamePattern = Pattern.compile("[А-ЯЁ][а-яё]+");
        Pattern namePattern = Pattern.compile("[А-ЯЁ][а-яё]+");
        Pattern patronymicPattern = Pattern.compile("[А-ЯЁ][а-яё]+");
        Pattern birthDatePattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        Pattern phoneNumberPattern = Pattern.compile("\\d{10}");
        Pattern genderPattern = Pattern.compile("[fm]");

        // Проходим по массиву слов и пытаемся распознать данные пользователя
        for (String word : words) {
            // Создаем объект Matcher для сравнения слова с шаблоном
            Matcher matcher;

            // Проверяем, является ли слово фамилией
            matcher = surnamePattern.matcher(word);
            if (matcher.matches()) {
                // Если да, то присваиваем его переменной surname
                surname = word;
                // Продолжаем цикл со следующим словом
                continue;
            }

            // Проверяем, является ли слово именем
            matcher = namePattern.matcher(word);
            if (matcher.matches()) {
                // Если да, то присваиваем его переменной name
                name = word;
                // Продолжаем цикл со следующим словом
                continue;
            }

            // Проверяем, является ли слово отчеством
            matcher = patronymicPattern.matcher(word);
            if (matcher.matches()) {
                // Если да, то присваиваем его переменной patronymic
                patronymic = word;
                // Продолжаем цикл со следующим словом
                continue;
            }

            // Проверяем, является ли слово датой рождения
            matcher = birthDatePattern.matcher(word);
            if (matcher.matches()) {
                // Если да, то присваиваем его переменной birthDate
                birthDate = word;
                // Продолжаем цикл со следующим словом
                continue;
            }

            // Проверяем, является ли слово номером телефона
            matcher = phoneNumberPattern.matcher(word);
            if (matcher.matches()) {
                // Если да, то преобразуем его в число и присваиваем переменной phoneNumber
                phoneNumber = Long.parseLong(word);
                // Продолжаем цикл со следующим словом
                continue;
            }

            // Проверяем, является ли слово полом
            matcher = genderPattern.matcher(word);
            if (matcher.matches()) {
                // Если да, то преобразуем его в символ и присваиваем переменной gender
                gender = word.charAt(0);
                // Продолжаем цикл со следующим словом
                continue;
            }

            // Если ни один из шаблонов не подошел, то бросаем исключение с сообщением об ошибке
            throw new IllegalArgumentException("Неверный формат данных: " + word);
        }

        // Проверяем, что все данные пользователя были распознаны
        if (surname == null || name == null || patronymic == null || birthDate == null || phoneNumber == 0 || gender == '\0') {
            // Если нет, то бросаем исключение с сообщением об ошибке
            throw new IllegalStateException("Не все данные пользователя были распознаны");
        }

        // Создаем строку с данными пользователя в требуемом формате
        String userData = surname + name + patronymic + birthDate + " " + phoneNumber + gender;

        // Создаем объект File для работы с файлом, название которого равно фамилии пользователя
        File file = new File(surname + ".txt");

        // Объявляем объект BufferedWriter для записи в файл
        BufferedWriter writer = null;

        try {
            // Создаем объект BufferedWriter с указанием кодировки UTF-8
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));

            // Записываем строку с данными пользователя в файл с переносом строки
            writer.write(userData + "\n");

            // Сохраняем изменения в файле
            writer.flush();
        } catch (IOException e) {
            // Если возникла ошибка ввода-вывода, то выводим стектрейс исключения
            e.printStackTrace();
        } finally {
            // В любом случае закрываем объект BufferedWriter, если он был создан
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // Если возникла ошибка при закрытии, то выводим стектрейс исключения
                    e.printStackTrace();
                }
            }
        }

        // Выводим сообщение об успешном выполнении программы
        System.out.println("Программа успешно выполнена");
    }
}
