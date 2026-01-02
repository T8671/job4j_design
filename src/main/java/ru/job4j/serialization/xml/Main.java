package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        final BookCharacter wanLin = new BookCharacter(
                true,
                30,
                "Wan Lin",
                new Origin("China", "Modern"),
                new String[]{"Ancient Scroll", "Magic Sword"});

        /* Преобразуем объект person в формат XML. */
        JAXBContext context = JAXBContext.newInstance(BookCharacter.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(wanLin, System.out);

        /* Вывод формата XML в файл */
        marshaller.marshal(wanLin, new File("data/book_character.xml"));
    }
}
