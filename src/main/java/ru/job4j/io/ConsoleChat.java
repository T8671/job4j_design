package ru.job4j.io;

//import java.io.IO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> phrases = readPhrases();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        List<String> log = new ArrayList<>();
        boolean canRespond = true;

        System.out.println("Бот активен. Введите сообщение.");

        String userInput = scanner.nextLine();
        while (!OUT.equals(userInput)) {
            log.add(timestamp() + " User: " + userInput);

            if (STOP.equals(userInput)) {
                canRespond = false;
                System.out.println("Бот замолчал. Он не будет отвечать, пока вы не напишете \"продолжить\".");
            } else if (CONTINUE.equals(userInput)) {
                canRespond = true;
                System.out.println("Бот снова активен. Введите сообщение.");
            } else if (canRespond) {
                String answer = phrases.get(random.nextInt(phrases.size()));
                log.add(timestamp() + " Bot: " + answer);
                System.out.println(answer);
            }
            userInput = scanner.nextLine();
        }
        System.out.println("Чат завершён. Лог сохранён в файл.");
        saveLog(log);
    }

    private String timestamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private List<String> readPhrases() throws IOException {
        return Files.readAllLines(Path.of(botAnswers));
    }

    private void saveLog(List<String> log) throws IOException {
        Files.write(Path.of(path), log);
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat consoleChat = new ConsoleChat("data/logBot.txt", "data/bot.txt");
        consoleChat.run();
    }
}
