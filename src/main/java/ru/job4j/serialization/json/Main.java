package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {

        final BookCharacter wanLin = new BookCharacter(
                true,
                30,
                "Wan Lin",
                new Origin("China", "Modern"),
                new String[]{"Ancient Scroll", "Magic Sword"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(wanLin));

        /* Создаём новую json-строку с модифицированными данными*/
        final String personJson =
                "{"
                        + "\"isProtagonist\":true,"
                        + "\"age\":35,"
                        + "\"name\":\"Wan Lin\","
                        + "\"origin\": { \"country\": \"China\", \"era\": \"Modern\" },"
                        + "\"artifacts\": [\"Heaven Defying Bead\",\"Soul Lasher\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        final BookCharacter personMod = gson.fromJson(personJson, BookCharacter.class);
        System.out.println(personMod);
    }
}
