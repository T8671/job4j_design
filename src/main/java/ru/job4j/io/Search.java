package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {

        validate(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    private static void validate(String[] args) {
        Path dir = Paths.get(args[0]);
        if (args.length != 2) {
            throw new IllegalArgumentException(""" 
                    There should be exactly 2 parameters.
                    
                    Usage example:
                        1st parameter: args[0] - the search directory path.
                        2nd parameter: args[1] - the file extension (e.g., .js, .txt).
                    """);
        }

        if (!Files.exists(dir)) {
            throw new IllegalArgumentException("""
                    The first parameter must be an existing directory path.
                    
                    Usage example:
                        args[0] - search directory path (e.g., C:\\projects\\job4j_design)
                        args[1] - file extension (e.g., .js, .txt)
                    """);
        }

        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("""
                    The first parameter should be a directory, not a file.
                    
                    Usage example:
                        args[0] - path to the search directory (e.g., C:\\projects\\job4j_design)
                        args[1] - file extension (e.g., .js, .txt)
                    """);
        }

        if (!(args[1]).startsWith(".")) {
            throw new IllegalArgumentException("""
                    The second parameter must be a file extension starting with a dot:
                    
                    Usage example:
                        args[1] - file extension (e.g., .js, .txt)
                    """);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
