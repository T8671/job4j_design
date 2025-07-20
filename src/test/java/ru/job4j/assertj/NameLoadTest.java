package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("collection contains no data");
    }

    @Test
    void checkLength() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parce)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkMissingEqualSign() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"invalid-format"};
        assertThatThrownBy(() -> nameLoad.parce(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: invalid-format does not contain the symbol '='");
    }

    @Test
    void checkEqualSignAtStart() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"=value"};
        assertThatThrownBy(() -> nameLoad.parce(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: =value does not contain a key");
    }

    @Test
    void checkEqualSignAtEnd() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"key="};
        assertThatThrownBy(() -> nameLoad.parce(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: key= does not contain a value");

    }
}