package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> filePropertyListMap = new HashMap<>();

    public Map<FileProperty, List<Path>> getAllFiles() {
        return filePropertyListMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty property = new FileProperty(attrs.size(), file.getFileName().toString());
        filePropertyListMap.computeIfAbsent(property, k -> new ArrayList<>()).add(file);
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : filePropertyListMap.entrySet()) {
            List<Path> paths = entry.getValue();
            if (paths.size() > 1) {
                for (Path path : paths) {
                    System.out.println(path);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        visitor.printDuplicates();
    }
}
