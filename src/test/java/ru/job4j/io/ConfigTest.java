package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenWithCommentsAndEmptyLines() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("url"))
                .isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
        assertThat(config.value("username")).isEqualTo("postgres");
        assertThat(config.value("password")).isEqualTo("password");
    }

    @Test
    void whenEmptyKeyThenException() {
        String path = "./data/invalid_empty_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEmptyValueThenException() {
        String path = "./data/invalid_empty_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoEqualsSignThenException() {
        String path = "./data/invalid_no_equals.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEqualsOnlyThenException() {
        String path = "./data/invalid_equals_only.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenExtraEqualsInValueThenParsedCorrectly() {
        String path = "./data/valid_extra_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("a")).isEqualTo("1=2");
        assertThat(config.value("b")).isEqualTo("value=");
    }
}