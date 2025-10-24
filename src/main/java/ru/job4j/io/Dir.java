package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Эта не директория: %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("%s : %d%n", subfile.getName(), subfile.length());
        }
    }
}
