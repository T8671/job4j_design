package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public class HomeWork {

    public static void main(String[] args) {
        Map<Integer, String> storage = new HashMap<>();
        storage.putIfAbsent(1, "one");
        storage.putIfAbsent(1, "two");
        System.out.println(storage);
    }
}
