package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            List<Boolean> result = Arrays
                    .stream(text.toString().split(System.lineSeparator()))
                    .map(s -> Integer.parseInt(s) % 2 == 0)
                    .toList();
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
