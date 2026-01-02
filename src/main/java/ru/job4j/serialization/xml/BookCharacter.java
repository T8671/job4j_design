package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement
public class BookCharacter {

    @XmlElement(required = true)
    private final boolean isProtagonist;

    @XmlElement(required = true)
    private final int age;

    @XmlElement(required = true)
    private final String name;

    @XmlElement(required = true)
    private final Origin origin;

    @XmlElementWrapper(name = "artifacts")
    @XmlElement(name = "artifact")
    private final String[] artifacts;

    public BookCharacter() {
        this.isProtagonist = false;
        this.age = 0;
        this.name = "";
        this.origin = null;
        this.artifacts = new String[0];
    }

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
