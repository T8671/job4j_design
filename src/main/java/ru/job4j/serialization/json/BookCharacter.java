package ru.job4j.serialization.json;

import java.util.Arrays;

public class BookCharacter {

    private final boolean isProtagonist;
    private final int age;
    private final String name;
    private final Origin origin;
    private final String[] artifacts;

    public BookCharacter(boolean isProtagonist, int age, String name, Origin origin, String[] artifacts) {
        this.isProtagonist = isProtagonist;
        this.age = age;
        this.name = name;
        this.origin = origin;
        this.artifacts = artifacts;
    }

    @Override
    public String toString() {
        return "BookCharacter{"
                + "isProtagonist=" + isProtagonist
                + ", age=" + age
                + ", name=" + name
                + ", origin=" + origin
                + ", artifacts=" + Arrays.toString(artifacts)
                + '}';
    }
}
