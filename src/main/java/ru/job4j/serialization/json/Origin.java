package ru.job4j.serialization.json;

public class Origin {

    private final String country;
    private final String era;

    public Origin(String country, String era) {
        this.country = country;
        this.era = era;
    }

    @Override
    public String toString() {
        return "Origin{"
                + "country='" + country + '\''
                + ", era='" + era + '\''
                + '}';
    }
}