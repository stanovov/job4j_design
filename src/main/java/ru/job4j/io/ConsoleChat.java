package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String botStatus = CONTINUE;
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            in.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (answers.size() == 0) {
            throw new IllegalArgumentException("File with phrases is not filled");
        }
        Random random = new Random();
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)),
                false, StandardCharsets.UTF_8);
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {
            while (!botStatus.equals(OUT)) {
                String consoleInput = consoleIn.readLine();
                String check = consoleInput.toLowerCase();
                boolean isStatus = check.equals(OUT) || check.equals(STOP)
                                        || check.equals(CONTINUE);
                if (isStatus) {
                    botStatus = check;
                }
                LocalDateTime currentDateTime = LocalDateTime.now();
                String formattedCurrDate = currentDateTime.format(FORMATTER);
                out.println(formattedCurrDate + " USER: " + consoleInput);
                if (botStatus.equals(CONTINUE)) {
                    String botAnswer = answers.get(random.nextInt(answers.size()));
                    System.out.println(botAnswer);
                    out.println(formattedCurrDate + " BOT: " + botAnswer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "./data/dialog.txt",
                "./data/botAnswers.txt"
        );
        cc.run();
    }
}