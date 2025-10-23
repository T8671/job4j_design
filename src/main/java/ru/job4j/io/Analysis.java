package ru.job4j.io;

import java.io.*;

public class Analysis {
    public static void unavailable(String source, String target) {

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {

            process(reader, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void process(BufferedReader reader, PrintWriter writer) throws IOException {
        String line;
        String start = null;
        boolean down = false;

        while ((line = reader.readLine()) != null) {

            line = line.trim();
            String[] parts = line.split("\\s+");

            if (line.isEmpty()) {
                continue;
            }
            if (parts.length < 2) {
                continue;
            }
            if (!Character.isDigit(parts[0].charAt(0))) {
                continue;
            }

            String status = parts[0];
            String time = parts[1];
            boolean bad = "400".equals(status) || "500".equals(status);
            boolean good = "200".equals(status) || "300".equals(status);

            if (bad && !down) {
                down = true;
                start = time;
            }

            if (good && down) {
                down = false;
                writer.println(start + ";" + time + ";");
            }
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
