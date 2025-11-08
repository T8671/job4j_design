package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws IOException {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");

        List<String> lines = readLines(path);
        String[] columns = lines.get(0).split(delimiter);
        List<Integer> indexes = getIndexes(columns, filters);
        List<String> output = filterLines(lines, indexes, delimiter);

        writeOutput(output, argsName.get("out"));
    }

    private static List<String> readLines(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

    private static List<Integer> getIndexes(String[] columns, String[] filters) {
        List<Integer> indexes = new ArrayList<>();
        for (String filter : filters) {
            for (int i = 0; i < columns.length; i++) {
                if (filter.equals(columns[i])) {
                    indexes.add(i);
                    break;
                }
            }
        }
        return indexes;
    }

    private static List<String> filterLines(List<String> lines, List<Integer> indexes, String delimiter) {
        List<String> output = new ArrayList<>();

        // Добавляем заголовок
        String[] headers = lines.get(0).split(delimiter);
        StringJoiner headerJoiner = new StringJoiner(delimiter);
        indexes.forEach(i -> headerJoiner.add(headers[i]));
        output.add(headerJoiner.toString());

        // Обрабатываем строки
        for (int i = 1; i < lines.size(); i++) {
            String[] row = lines.get(i).split(delimiter);
            StringJoiner rowJoiner = new StringJoiner(delimiter);

            for (Integer index : indexes) {
                if (index < row.length) {
                    rowJoiner.add(row[index]);
                } else {
                    rowJoiner.add(""); // пустая ячейка, если данных нет
                }
            }
            output.add(rowJoiner.toString());
        }
        return output;
    }

    private static void writeOutput(List<String> output, String out) throws IOException {
        if ("stdout".equals(out)) {
            output.forEach(System.out::println);
        } else {
            Files.write(Path.of(out), output);
        }
    }

    private static void validate(ArgsName argsName) {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        if (path == null
                || delimiter == null
                || out == null
                || filter == null) {
            throw new IllegalArgumentException("All arguments -path, -delimiter, -out, -filter must be provided.");
        }

        if (!Files.exists(Path.of(path))) {
            throw new IllegalArgumentException("The file at -path does not exist: " + path);
        }

        if (delimiter.length() != 1) {
            throw new IllegalArgumentException("Delimiter must be a single character.");
        }

        if (!(out.equals("stdout")
                || out.endsWith(".csv")
                || out.endsWith(".txt"))) {
            throw new IllegalArgumentException("Output must be 'stdout' or a file with .csv/.txt extension.");
        }

        if (!filter.contains(",")) {
            throw new IllegalArgumentException("Filter must contain at least two fields separated by a comma.");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}


