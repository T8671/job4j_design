package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target, Path root) {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target))) {

            for (Path file : sources) {
                Path relativePath = root.relativize(file);
                out.putNextEntry(new ZipEntry(relativePath.toString()));

                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    out.write(input.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));

            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        Path directory = Paths.get((argsName.get("d")));
        String exclude = argsName.get("e");
        String output = argsName.get("o");

        if (!Files.exists((directory))) {
            throw new IllegalArgumentException("Directory does not exist: " + directory);
        }
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Provided path is not a directory: " + directory);
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Exclude extension should start with '.' but got: " + exclude);
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Output file should end with .zip but got: " + output);
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        ArgsName argsName = ArgsName.of(args);
        validate(argsName);

        Path directory = Paths.get(argsName.get("d"));
        String exclude = argsName.get("e");
        File target = new File(argsName.get("o"));

        List<Path> sources = Search.search(directory,
                path -> !path.toFile().getName().endsWith(exclude));
        zip.packFiles(sources, target, directory);
    }
}
