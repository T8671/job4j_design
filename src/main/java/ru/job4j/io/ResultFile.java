package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultFile {

    private static final Path FILE = Paths.get("data", "dataresult.txt");
    private static final String NL = System.lineSeparator();

    public static void main(String[] args) {
        String table = multiplicationTable(10);
        write(table);
    }

    private static String multiplicationTable(int size) {
        return IntStream.rangeClosed(1, size)
                .mapToObj(i -> IntStream.rangeClosed(1, size)
                        .mapToObj(j -> String.format("%4d", i * j))
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining(NL, "", NL));
    }

    private static void write(String data) {
        try (FileOutputStream output = new FileOutputStream(String.valueOf(FILE))) {
            output.write(data.getBytes());
            output.write(NL.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
