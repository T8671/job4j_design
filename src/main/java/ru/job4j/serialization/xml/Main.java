package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {

        final BookCharacter wanLin = new BookCharacter(
                true,
                30,
                "Wan Lin",
                new Origin("China", "Modern"),
                new String[]{"Ancient Scroll", "Magic Sword"});

        /* Преобразуем объект wanLin в формат XML. */
        JAXBContext context = JAXBContext.newInstance(BookCharacter.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(wanLin, System.out);

        /* Вывод формата XML в файл */
        marshaller.marshal(wanLin, new File("data/book_character.xml"));

        /* Преобразуем формат XML в объект wanLin. */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BookCharacter result = (BookCharacter) unmarshaller.unmarshal(new File("data/book_character.xml"));
        System.out.println(result);
    }
}
