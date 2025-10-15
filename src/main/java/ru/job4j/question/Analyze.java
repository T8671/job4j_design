package ru.job4j.question;

import java.util.*;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {

        int added = 0;
        int changed = 0;

        Map<Integer, String> prevById = new HashMap<>(previous.size() * 2);
        Set<Integer> seenPrev = new HashSet<>();

        for (User p : previous) {
            if (p != null) {
                prevById.putIfAbsent(p.getId(), p.getName());
            }
        }

        for (User c : current) {
            if (c == null || seenPrev.contains(c.getId())) {
                continue;
            }

            if (!prevById.containsKey(c.getId())) {
                added++;
                continue;
            } else if (!Objects.equals(prevById.get(c.getId()), c.getName())) {
                changed++;
            }
            seenPrev.add(c.getId());
        }
        int deleted = prevById.size() - seenPrev.size();
        return new Info(added, changed, deleted);
    }
}
