package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LogFilter {

    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    private List<String> filter() {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .filter(line -> {
                        String[] parts = line.split("\\s+");
                        return parts.length > 1 && "404".equals(parts[parts.length - 2]);
                    })
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
