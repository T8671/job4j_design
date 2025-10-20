package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty() && !s.startsWith("#") && !s.startsWith("!"))
                    .forEach(line -> {
                        int idx = line.indexOf('=');
                        if (idx < 0 || idx == 0 || idx == line.length() - 1) {
                            throw new IllegalArgumentException("Invalid line: " + line);
                        } else {
                            String key = line.substring(0, idx).trim();
                            String value = line.substring(idx + 1);
                            values.put(key, value);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
