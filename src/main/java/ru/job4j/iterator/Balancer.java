package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Balancer {

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        List<ArrayList<Integer>> validNotes = nodes.stream()
                .filter(Objects::nonNull)
                .toList();

        if (validNotes.isEmpty()) {
            return;
        }

        int index = 0;
        while (source.hasNext()) {
            validNotes.get(index).add(source.next());
            index = (index + 1) % validNotes.size();
        }
    }
}
