package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Origin {

    private String country;
    private String era;

    public Origin() {
    }

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
